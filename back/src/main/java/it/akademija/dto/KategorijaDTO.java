package it.akademija.dto;

public class KategorijaDTO {

    private Long id;
    private String pavadinimas;

    public KategorijaDTO() {
	super();
    }

    public KategorijaDTO(Long id, String pavadinimas) {
	super();
	this.id = id;
	this.pavadinimas = pavadinimas;
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

}
