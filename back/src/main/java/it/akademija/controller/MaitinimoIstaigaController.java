package it.akademija.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.akademija.dto.MaitinimoIstaigaDTO;
import it.akademija.entity.MaitinimoIstaiga;
import it.akademija.service.MaitinimoIstaigaService;

@RestController
@RequestMapping(path = "/api/maitinimoistaiga")
public class MaitinimoIstaigaController {
    
    @Autowired
    private MaitinimoIstaigaService maitinimoIstaigaService;
    
    @GetMapping("/")
    public Set<MaitinimoIstaiga> getAllMaitinimoIstaiga() {
	return maitinimoIstaigaService.getAllMaitinimoIstaiga();
    }
    
    @GetMapping("/{id}")
    public MaitinimoIstaigaDTO getMaitinimoIstaiga(@PathVariable Long id) {
	return maitinimoIstaigaService.getMaitinimoIstaiga(id);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/")
    public ResponseEntity<String> createNewMaitinimoIstaiga(@RequestBody MaitinimoIstaigaDTO maitinimoIstaigaDTO) {
	return maitinimoIstaigaService.createNewMaitinimoIstaiga(maitinimoIstaigaDTO);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/")
    public ResponseEntity<String> editMaitinimoIstaiga(@RequestBody MaitinimoIstaigaDTO maitinimoIstaigaDTO) {
	return maitinimoIstaigaService.editMaitinimoIstaiga(maitinimoIstaigaDTO);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaitinimoIstaiga(@PathVariable Long id) {
	return maitinimoIstaigaService.deleteMaitinimoIstaiga(id);
    }

}
