package it.akademija.service;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.akademija.dto.KategorijaDTO;
import it.akademija.entity.Kategorija;
import it.akademija.repository.KategorijaDAO;

@Service
public class KategorijaService {
    
    @Autowired
    private KategorijaDAO kategorijaDAO;
    
    @Transactional
    public Set<KategorijaDTO> getAllKategorija() {
	return kategorijaDAO.getAllKategorija();
    }
    
    @Transactional
    public KategorijaDTO getKategorija(Long id) {
	return kategorijaDAO.getKategorija(id);
    }
    
    @Transactional
    public ResponseEntity<String> createKategorija(KategorijaDTO kategorijaDTO) {
	if (kategorijaDAO.findByPavadinimas(kategorijaDTO.getPavadinimas()).isPresent()) {
	    return new ResponseEntity<String>("Tokia kategorija jau egzistuoja", HttpStatus.CONFLICT);
	}
	Kategorija kategorija = new Kategorija ();
	kategorija.setPavadinimas(kategorijaDTO.getPavadinimas());
	kategorijaDAO.save(kategorija);
	return new ResponseEntity<String>("Kategorija išsaugota sėkmingai", HttpStatus.OK);
    }
    
    @Transactional
    public ResponseEntity<String> editKategorija(KategorijaDTO kategorijaDTO) {
	Optional<Kategorija> optionalKategorija = kategorijaDAO.findById(kategorijaDTO.getId());
	if (optionalKategorija.isEmpty()) {
	    return new ResponseEntity<String>("Tokia kategorija neegzistuoja", HttpStatus.CONFLICT);
	}
	Kategorija kategorija = optionalKategorija.get();
	kategorija.setPavadinimas(kategorijaDTO.getPavadinimas());
	return new ResponseEntity<String>("Kategorija pakeista sėkmingai", HttpStatus.OK);
    }
    
    @Transactional
    public ResponseEntity<String> deleteKategorija(Long id) {
	if (!kategorijaDAO.existsById(id)) {
	    return new ResponseEntity<String>("Tokia kadegorija neegzistuoja", HttpStatus.CONFLICT);
	}
	kategorijaDAO.deleteById(id);
	return new ResponseEntity<String>("Kategorija ištrinta sėkmingai", HttpStatus.OK);
    }

}
