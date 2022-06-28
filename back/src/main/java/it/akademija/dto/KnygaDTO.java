package it.akademija.dto;

public class KnygaDTO {

    private Long id;
    private String pavadinimas;
    private String santrauka;
    private String isbn;
    private byte[] nuotrauka;
    private Long puslapiai;
    private Long kategorijaId;

    public KnygaDTO() {
	super();
    }

    public KnygaDTO(Long id, String pavadinimas, String santrauka, String isbn,
	    byte[] nuotrauka, Long puslapiai, Long kategorijaId) {
	super();
	this.id = id;
	this.pavadinimas = pavadinimas;
	this.santrauka = santrauka;
	this.isbn = isbn;
	this.nuotrauka = nuotrauka;
	this.puslapiai = puslapiai;
	this.kategorijaId = kategorijaId;
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

    public Long getKategorijaId() {
	return kategorijaId;
    }

    public void setKategorijaId(Long kategorijaId) {
	this.kategorijaId = kategorijaId;
    }

}
