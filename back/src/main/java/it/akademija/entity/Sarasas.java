package it.akademija.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.akademija.user.User;

@Entity
public class Sarasas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JsonIgnore
    private Set<Knyga> knygos;

    public Sarasas() {
	super();
    }

    public Sarasas(Long id, User user, Set<Knyga> knygos) {
	super();
	this.id = id;
	this.user = user;
	this.knygos = knygos;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Set<Knyga> getKnygos() {
	return knygos;
    }

    public void setKnygos(Set<Knyga> knygos) {
	this.knygos = knygos;
    }

}
