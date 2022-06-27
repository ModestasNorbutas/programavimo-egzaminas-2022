package it.akademija.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.akademija.dto.PatiekalasDTO;
import it.akademija.entity.Patiekalas;
import it.akademija.repository.MaitinimoIstaigaDAO;
import it.akademija.repository.PatiekalasDAO;

@Service
public class PatiekalasService {
    
    @Autowired
    private PatiekalasDAO patiekalasDAO;
    
    @Autowired
    private MaitinimoIstaigaDAO maitinimoIstaigaDAO;
    
    @Transactional
    public Set<Patiekalas> getAllPatiekalasByMaitinimoIstaigaId(Long Id) {
	return patiekalasDAO.getAllPatiekalasByMaitinimoIstaigaId(Id);
    }
    
    @Transactional
    public PatiekalasDTO getPatiekalas(Long id) {
	return patiekalasDAO.getPatiekalasById(id);
    }
    
    @Transactional
    public ResponseEntity<String> createNewPatiekalas(PatiekalasDTO patiekalasDTO) {
	Patiekalas patiekalas = new Patiekalas(
		patiekalasDTO.getPavadinimas(),
		patiekalasDTO.getAprasymas(),
		maitinimoIstaigaDAO.getById(patiekalasDTO.getMaitinimoIstaigaId())
		);
	patiekalasDAO.save(patiekalas);
	return new ResponseEntity<String>("Patiekalas išsaugotas sėkmingai", HttpStatus.OK);
    }
    
    @Transactional
    public ResponseEntity<String> editPatiekalas(PatiekalasDTO patiekalasDTO) {
	if (!patiekalasDAO.existsById(patiekalasDTO.getId())) {
	    return new ResponseEntity<String>("Toks patiekalas neegzistuoja", HttpStatus.CONFLICT);
	}
	Patiekalas patiekalas = patiekalasDAO.getById(patiekalasDTO.getId());
	    patiekalas.setPavadinimas(patiekalasDTO.getPavadinimas());
	    patiekalas.setAprasymas(patiekalasDTO.getAprasymas());
	    patiekalasDAO.save(patiekalas);
	    return new ResponseEntity<String>("Patiekalas atnaujintas sėkmingai", HttpStatus.OK);
    }
    
    @Transactional
    public ResponseEntity<String> deletePatiekalas(Long id) {
	if (!patiekalasDAO.existsById(id)) {
	    return new ResponseEntity<String>("Toks patiekalas neegzistuoja", HttpStatus.CONFLICT);
	}
	patiekalasDAO.deleteById(id);
	return new ResponseEntity<String>("Patiekalas ištrintas sėkmingai", HttpStatus.OK);
    }

}
