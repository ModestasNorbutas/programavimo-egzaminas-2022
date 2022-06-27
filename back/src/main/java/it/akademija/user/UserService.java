package it.akademija.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.akademija.role.Role;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Autowired		
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Lazy
	private SessionRegistry sessionRegistry;
	
	/**
	 * Finds User by username
	 * 
	 * @param id
	 */
	public User getUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username + " not found.");
		} else {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					AuthorityUtils.createAuthorityList(new String[] { "ROLE_" + user.getRole().toString() }));
		}
	}

	/**
	 * Create new user with specified parameters. Deletes FirstUser "admin" which
	 * was initialized at start up if there are other users with ADMIN authorization
	 * in the user repository
	 * 
	 * @param userData data for new user
	 */
	@Transactional
	public void createUser(UserDTO userData) {
		User newUser = new User();

		newUser.setName(userData.getName());
		newUser.setSurname(userData.getSurname());
		newUser.setEmail(userData.getEmail());
		newUser.setRole(Role.valueOf(userData.getRole()));
		newUser.setUsername(userData.getUsername());
		newUser.setPassword(passwordEncoder.encode(userData.getUsername()));
		userDao.saveAndFlush(newUser);
	}

	/**
	 * 
	 * Delete user with a specified username. If user role equals USER, deletes
	 * associated Application and ParentDetails entries in the database and
	 * increases number of available places in approved Kindergarten if applicable.
	 * 
	 * @param username
	 */
	@Transactional
	public void deleteUser(String username) {

		User user = findByUsername(username);

		if (user.getRole().equals(Role.ADMIN) && userDao.findByRole(Role.ADMIN).size() == 1) {

			userDao.save(new User(Role.ADMIN, "admin", "admin", "admin", "admin",
					passwordEncoder.encode("admin")));
		}

		expireSession(user);

		userDao.deleteByUsername(username);
	}

	/**
	 * 
	 * Expire session of logged in user if ADMIN deletes their account
	 * 
	 * @param user
	 */
	private void expireSession(User user) {

		List<Object> principals = sessionRegistry.getAllPrincipals();
		for (Object principal : principals) {
			UserDetails pUser = (UserDetails) principal;
			if (pUser.getUsername().equals(user.getUsername())) {
				for (SessionInformation activeSession : sessionRegistry.getAllSessions(principal, false)) {
					activeSession.expireNow();
				}
			}
		}
	}

	/**
	 * User can delete their own data. GDPR related functionality that deletes all
	 * user related entries from database.
	 */
	@Transactional
	public void deleteMyUserData() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		deleteUser(username);

	}


	/**
	 * 
	 * Finds user with a specified username. Don't return User entity via REST.
	 * 
	 * @param username
	 * @return User entity (includes sensitive data)
	 */
	@Transactional(readOnly = true)
	public User findByUsername(String username) {

		return userDao.findByUsername(username);
	}


	/**
	 * Changes users password
	 * 
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return true or false
	 */
	@Transactional
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		User user = findByUsername(username);
		String currentPassword = user.getPassword();
		if (passwordEncoder.matches(oldPassword, currentPassword)) {
			user.setPassword(passwordEncoder.encode(newPassword));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Updates fields for user with specified username. Field for setting user role,
	 * password or username can not be updated. Any user can update their own data.
	 * 
	 * @param userData new user data
	 * @param username
	 * @return updated user
	 */
	@Transactional
	public User updateUserData(UserDTO userData, String username) {

		User user = findByUsername(username);

		user.setName(userData.getName());
		user.setSurname(userData.getSurname());
		user.setEmail(userData.getEmail());

		return userDao.save(user);
	}
	

}
