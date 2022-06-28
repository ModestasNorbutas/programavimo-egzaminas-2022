package it.akademija.service;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.akademija.dto.KnygaDTO;
import it.akademija.entity.Knyga;
import it.akademija.repository.KategorijaDAO;
import it.akademija.repository.KnygaDAO;

@Service
public class KnygaService {
    
    @Autowired
    private KnygaDAO knygaDAO;
    
    @Autowired
    private KategorijaDAO kategorijaDAO;
    
    @Transactional
    public Set<KnygaDTO> getAllKnyga() {
	return knygaDAO.getAllKnyga();
    }
    
    @Transactional
    public Set<KnygaDTO> getAllKnygaByKategorija(Long kategorijaId) {
	return knygaDAO.getAllKnygaByKategorija(kategorijaId);
    }
    
    @Transactional
    public Set<KnygaDTO> getAllKnygaByPavadinimas(String pavadinimas) {
	return knygaDAO.getAllKnygaByPavadinimas(pavadinimas.trim());
    }
    
    @Transactional
    public KnygaDTO getKnyga(Long id) {
	return knygaDAO.getKnygaById(id);
    }
    
    @Transactional
    public ResponseEntity<String> createKnyga(KnygaDTO knygaDTO) {
	Knyga knyga = new Knyga (
		knygaDTO.getPavadinimas(),
		knygaDTO.getSantrauka(),
		knygaDTO.getIsbn(),
		knygaDTO.getNuotrauka(),
		knygaDTO.getPuslapiai(),
		kategorijaDAO.getById(knygaDTO.getKategorijaId())
		);
	knygaDAO.save(knyga);
	return new ResponseEntity<String>("Knyga išsaugota sėkmingai", HttpStatus.OK);
    }
    
    @Transactional
    public ResponseEntity<String> editKnyga(KnygaDTO knygaDTO) {
	Optional<Knyga> optionalKnyga = knygaDAO.findById(knygaDTO.getId());
	if (optionalKnyga.isEmpty()) {
	    return new ResponseEntity<String>("Tokia knyga neegzistuoja", HttpStatus.CONFLICT);
	}
	Knyga knyga = optionalKnyga.get();
	knyga.setPavadinimas(knygaDTO.getPavadinimas());
	knyga.setSantrauka(knygaDTO.getSantrauka());
	knyga.setIsbn(knygaDTO.getIsbn());
	knyga.setNuotrauka(knygaDTO.getNuotrauka());
	knyga.setPuslapiai(knygaDTO.getPuslapiai());
	knyga.setKategorija(kategorijaDAO.getById(knygaDTO.getKategorijaId()));
	knygaDAO.save(knyga);
	return new ResponseEntity<String>("Knyga atnaujinta sėkmingai", HttpStatus.OK);
    }
    
    @Transactional
    public ResponseEntity<String> deleteKnyga(Long id) {
	if (!knygaDAO.existsById(id)) {
	    return new ResponseEntity<String>("Tokia knyga neegzistuoja", HttpStatus.CONFLICT);
	}
	knygaDAO.deleteById(id);
	return new ResponseEntity<String>("Knyga ištrinta sėkmingai", HttpStatus.OK);
    }
    

}
