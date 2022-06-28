package it.akademija.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kategorija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pavadinimas;

    @OneToMany
    @JsonIgnore
    private Set<Knyga> knyga;

    public Kategorija() {
	super();
    }

    public Kategorija(Long id, String pavadinimas, Set<Knyga> knyga) {
	super();
	this.id = id;
	this.pavadinimas = pavadinimas;
	this.knyga = knyga;
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

    public Set<Knyga> getKnyga() {
	return knyga;
    }

    public void setKnyga(Set<Knyga> knyga) {
	this.knyga = knyga;
    }

}
