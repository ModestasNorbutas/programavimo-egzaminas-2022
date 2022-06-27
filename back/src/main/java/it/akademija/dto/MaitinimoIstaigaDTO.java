package it.akademija.dto;

public class MaitinimoIstaigaDTO {

    private Long id;
    private String pavadinimas;
    private String kodas;
    private String adresas;

    public MaitinimoIstaigaDTO() {
	super();
    }

    public MaitinimoIstaigaDTO(Long id, String pavadinimas, String kodas,
	    String adresas) {
	super();
	this.id = id;
	this.pavadinimas = pavadinimas;
	this.kodas = kodas;
	this.adresas = adresas;
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

    @Override
    public String toString() {
	return "MaitinimoIstaigaDTO [id=" + id + ", pavadinimas=" + pavadinimas
		+ ", kodas=" + kodas + ", adresas=" + adresas + "]";
    }

}
