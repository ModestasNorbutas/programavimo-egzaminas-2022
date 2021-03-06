package it.akademija.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "user")
@RequestMapping(path = "/api/users")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 * Create new user. Method only accessible to ADMIN users
	 * 
	 * @param userInfo
	 */
	@Secured({ "ROLE_ADMIN" })
	@PostMapping(path = "/admin/createuser")
	@ApiOperation(value = "Create user", notes = "Creates user with data")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userInfo) {

		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

		LOG.info("** Usercontroller: kuriamas naujas naudotojas **");

		if (userService.findByUsername(userInfo.getUsername()) != null) {

			LOG.warn("Naudotojas [{}] bandė sukurti naują naudotoją su jau egzistuojančiu vardu [{}]", currentUsername,
					userInfo.getUsername());

			return new ResponseEntity<String>("Toks naudotojas jau egzistuoja!", HttpStatus.BAD_REQUEST);

		} else {

			userService.createUser(userInfo);

			LOG.info("Naudotojas [{}] sukūrė naują naudotoją [{}]", currentUsername, userInfo.getUsername());

			return new ResponseEntity<String>("Naudotojas sukurtas sėkmingai!", HttpStatus.CREATED);
		}
	}

	/**
	 * 
	 * Deletes user with specified username. Method only accessible to ADMIN users
	 * 
	 * @param username
	 */
	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping(path = "/admin/delete/{username}")
	@ApiOperation(value = "Delete user", notes = "Deletes user by username")
	public ResponseEntity<String> deleteUser(
			@ApiParam(value = "Username", required = true) @PathVariable final String username) {

		long id = userService.findByUsername(username).getUserId();

		if (userService.findByUsername(username) != null) {

			userService.deleteUser(username);

			LOG.info("** Usercontroller: trinamas naudotojas vardu [{}] **", username);

			return new ResponseEntity<String>("Naudotojas ištrintas sėkmingai", HttpStatus.OK);
		}

		LOG.warn("Naudotojas bandė ištrinti naudotoją neegzistuojančiu vardu [{}]", username);

		return new ResponseEntity<String>("Naudotojas tokiu vardu nerastas", HttpStatus.NOT_FOUND);
	}

	/**
	 * 
	 * Update user data
	 * 
	 * @param userData
	 * @return message
	 */
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER" })
	@PutMapping(path = "/update")
	@ApiOperation(value = "Update logged in user details")
	public ResponseEntity<String> updateUserData(@RequestBody UserDTO userData) {

		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		userService.updateUserData(userData, currentUserName);

		LOG.info("** Usercontroller: keiciami duomenys naudotojui vardu [{}] **", currentUserName);

		return new ResponseEntity<String>("Duomenys pakeisti sėkmingai", HttpStatus.OK);

	}

	/**
	 * Change user password for logged in user
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @return message
	 */
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER" })
	@PutMapping(path = "/updatepassword/{oldPassword}/{newPassword}")
	@ApiOperation(value = "Update logged in user password")
	public ResponseEntity<String> updateUserPassword(@PathVariable(value = "oldPassword") final String oldPassword,
			@PathVariable(value = "newPassword") final String newPassword) {

		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

		if (userService.changePassword(currentUsername, oldPassword, newPassword)) {

			LOG.info(" [{}] slaptažodis pakeistas sėkmingai. **", currentUsername);

			return new ResponseEntity<String>("Slaptažodis pakeistas sėkmingai", HttpStatus.OK);

		} else {

			LOG.warn(" [{}] įvedė neteisingą seną slaptažodį. **", currentUsername);

			return new ResponseEntity<String>("Neteisingas senas slaptažodis", HttpStatus.BAD_REQUEST);

		}
	}

	/**
	 * "Forget me" functionality which deletes all user related entries from
	 * database
	 * 
	 */
	@Secured({ "ROLE_USER" })
	@DeleteMapping(path = "/user/deletemydata")
	@ApiOperation(value = "Forget me - delete all user data")
	public void deleteMyUserData() {

		userService.deleteMyUserData();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
