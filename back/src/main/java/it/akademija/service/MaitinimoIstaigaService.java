package it.akademija.service;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.akademija.dto.MaitinimoIstaigaDTO;
import it.akademija.entity.MaitinimoIstaiga;
import it.akademija.repository.MaitinimoIstaigaDAO;

@Service
public class MaitinimoIstaigaService {
    
    @Autowired
    private MaitinimoIstaigaDAO maitinimoIstaigaDAO;
    
    @Transactional
    public Set<MaitinimoIstaiga> getAllMaitinimoIstaiga() {
	return maitinimoIstaigaDAO.getAllMaitinimoIstaiga();
    }
    
    @Transactional
    public MaitinimoIstaigaDTO getMaitinimoIstaiga(Long id) {
	MaitinimoIstaiga maitinimoIstaiga = maitinimoIstaigaDAO.getById(id);
	MaitinimoIstaigaDTO maitinimoIstaigaDTO = new MaitinimoIstaigaDTO(
		maitinimoIstaiga.getId(),
		maitinimoIstaiga.getPavadinimas(),
		maitinimoIstaiga.getKodas(),
		maitinimoIstaiga.getAdresas()
		);
	return maitinimoIstaigaDTO;
    }
    
    @Transactional
    public ResponseEntity<String> createNewMaitinimoIstaiga(MaitinimoIstaigaDTO maitinimoIstaigaDTO) {
	if (maitinimoIstaigaDAO.existsByPavadinimas(maitinimoIstaigaDTO.getPavadinimas())) {
	    return new ResponseEntity<String>("Tokia maitinimo įstaiga jau egzistuoja", HttpStatus.CONFLICT);
	}
	MaitinimoIstaiga newMaitinimoIstaiga = new MaitinimoIstaiga(
		maitinimoIstaigaDTO.getPavadinimas(),
		maitinimoIstaigaDTO.getKodas(),
		maitinimoIstaigaDTO.getAdresas()
		);
	maitinimoIstaigaDAO.save(newMaitinimoIstaiga);
	return new ResponseEntity<String>("Maitinimo įstaiga išsaugota sėkmingai", HttpStatus.OK);
    }
    
   @Transactional
   public ResponseEntity<String> editMaitinimoIstaiga(MaitinimoIstaigaDTO maitinimoIstaigaDTO) {
       Optional<MaitinimoIstaiga> optionalMaitinimoIstaiga = maitinimoIstaigaDAO.findById(maitinimoIstaigaDTO.getId());
       if (optionalMaitinimoIstaiga.isEmpty()) {
	   return new ResponseEntity<String>("Tokia maitinimo įstaiga neegzistuoja", HttpStatus.CONFLICT);
       }
       MaitinimoIstaiga newMaitinimoIstaiga = optionalMaitinimoIstaiga.get();
       newMaitinimoIstaiga.setPavadinimas(maitinimoIstaigaDTO.getPavadinimas());
       newMaitinimoIstaiga.setKodas(maitinimoIstaigaDTO.getKodas());
       newMaitinimoIstaiga.setAdresas(maitinimoIstaigaDTO.getAdresas());
       maitinimoIstaigaDAO.save(newMaitinimoIstaiga);
       return new ResponseEntity<String>("Maitinimo įstaiga redaguota sėkmingai", HttpStatus.OK);
   }
   
   @Transactional
   public ResponseEntity<String> deleteMaitinimoIstaiga(Long id) {
       if (maitinimoIstaigaDAO.findById(id).isEmpty()) {
	   return new ResponseEntity<String>("Tokia maitinimo įstaiga neegzistuoja", HttpStatus.CONFLICT);
       }
       maitinimoIstaigaDAO.deleteById(id);
       return new ResponseEntity<String>("Maitinimo įstaiga ištrinta sėkmingai", HttpStatus.OK);
   }

}
