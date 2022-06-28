package it.akademija.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.akademija.user.User;

@Entity
public class Knyga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pavadinimas;
    private String santrauka;
    private String isbn;
    private byte[] nuotrauka;
    private Long puslapiai;

    @ManyToOne
    @JoinColumn(name = "kategorija_id")
    private Kategorija kategorija;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JsonIgnore
    private Set<Sarasas> sarasai;

    public Knyga() {
	super();
    }

    public Knyga(Long id, String pavadinimas, String santrauka, String isbn,
	    byte[] nuotrauka, Long puslapiai, Kategorija kategorija, User user,
	    Set<Sarasas> sarasai) {
	super();
	this.id = id;
	this.pavadinimas = pavadinimas;
	this.santrauka = santrauka;
	this.isbn = isbn;
	this.nuotrauka = nuotrauka;
	this.puslapiai = puslapiai;
	this.kategorija = kategorija;
	this.user = user;
	this.sarasai = sarasai;
    }
    
    public Knyga(String pavadinimas, String santrauka, String isbn,
	    byte[] nuotrauka, Long puslapiai, Kategorija kategorija) {
	super();
	this.pavadinimas = pavadinimas;
	this.santrauka = santrauka;
	this.isbn = isbn;
	this.nuotrauka = nuotrauka;
	this.puslapiai = puslapiai;
	this.kategorija = kategorija;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getPavadinimas() {
	return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
	this.pavadinimas = pavadinimas;
    }

    public String getSantrauka() {
	return santrauka;
    }

    public void setSantrauka(String santrauka) {
	this.santrauka = santrauka;
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public byte[] getNuotrauka() {
	return nuotrauka;
    }

    public void setNuotrauka(byte[] nuotrauka) {
	this.nuotrauka = nuotrauka;
    }

    public Long getPuslapiai() {
	return puslapiai;
    }

    public void setPuslapiai(Long puslapiai) {
	this.puslapiai = puslapiai;
    }

    public Kategorija getKategorija() {
	return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
	this.kategorija = kategorija;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Set<Sarasas> getSarasai() {
	return sarasai;
    }

    public void setSarasai(Set<Sarasas> sarasai) {
	this.sarasai = sarasai;
    }

}
