package it.akademija.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Patiekalas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pavadinimas;
    private String aprasymas;

    @ManyToOne
    @JoinColumn(name = "istaiga_id")
    private MaitinimoIstaiga maitinimoIstaiga;

    public Patiekalas() {
	super();
    }

    public Patiekalas(String pavadinimas, String aprasymas,
	    MaitinimoIstaiga maitinimoIstaiga) {
	super();
	this.pavadinimas = pavadinimas;
	this.aprasymas = aprasymas;
	this.maitinimoIstaiga = maitinimoIstaiga;
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

    public String getAprasymas() {
	return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
	this.aprasymas = aprasymas;
    }

    public MaitinimoIstaiga getMaitinimoIstaiga() {
	return maitinimoIstaiga;
    }

    public void setMaitinimoIstaiga(MaitinimoIstaiga maitinimoIstaiga) {
	this.maitinimoIstaiga = maitinimoIstaiga;
    }

}
