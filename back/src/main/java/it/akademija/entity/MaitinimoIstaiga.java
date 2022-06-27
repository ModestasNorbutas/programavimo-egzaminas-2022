package it.akademija.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MaitinimoIstaiga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pavadinimas;
    private String kodas;
    private String adresas;

    @OneToMany
    @JsonIgnore
    private Set<Patiekalas> patiekalas;

    public MaitinimoIstaiga() {
	super();
    }

    public MaitinimoIstaiga(String pavadinimas, String kodas, String adresas) {
	super();
	this.pavadinimas = pavadinimas;
	this.kodas = kodas;
	this.adresas = adresas;
    }

    public Long getId() {
	return id;
    }

    public String getPavadinimas() {
	return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
	this.pavadinimas = pavadinimas;
    }

    public String getKodas() {
	return kodas;
    }

    public void setKodas(String kodas) {
	this.kodas = kodas;
    }

    public String getAdresas() {
	return adresas;
    }

    public void setAdresas(String adresas) {
	this.adresas = adresas;
    }

    public Set<Patiekalas> getPatiekalas() {
	return patiekalas;
    }

    public void setPatiekalas(Set<Patiekalas> patiekalas) {
	this.patiekalas = patiekalas;
    }

}
