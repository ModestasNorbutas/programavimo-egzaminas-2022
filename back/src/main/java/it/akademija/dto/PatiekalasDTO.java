package it.akademija.dto;

public class PatiekalasDTO {

    private Long id;
    private String pavadinimas;
    private String aprasymas;
    private Long maitinimoIstaigaId;

    public PatiekalasDTO() {
	super();
    }

    public PatiekalasDTO(Long id, String pavadinimas, String aprasymas,
	    Long maitinimoIstaigaId) {
	super();
	this.id = id;
	this.pavadinimas = pavadinimas;
	this.aprasymas = aprasymas;
	this.maitinimoIstaigaId = maitinimoIstaigaId;
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

    public String getAprasymas() {
	return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
	this.aprasymas = aprasymas;
    }

    public Long getMaitinimoIstaigaId() {
	return maitinimoIstaigaId;
    }

    public void setMaitinimoIstaigaId(Long maitinimoIstaigaId) {
	this.maitinimoIstaigaId = maitinimoIstaigaId;
    }

}
