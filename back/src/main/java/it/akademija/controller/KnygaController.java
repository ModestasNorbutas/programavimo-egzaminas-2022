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

import it.akademija.dto.KnygaDTO;
import it.akademija.service.KnygaService;

@RestController
@RequestMapping(path = "/api/knyga")
public class KnygaController {
    
    @Autowired
    private KnygaService knygaService;
    
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/all")
    public Set<KnygaDTO> getAllKnyga() {
	return knygaService.getAllKnyga();
    }
    
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/kategorija/{kategorijaId}")
    public Set<KnygaDTO> getAllKnygaByKategorija(@PathVariable Long kategorijaId) {
	return knygaService.getAllKnygaByKategorija(kategorijaId);
    }
    
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public KnygaDTO getKnyga(@PathVariable Long id) {
	return knygaService.getKnyga(id);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/")
    public ResponseEntity<String> createKnyga(@RequestBody KnygaDTO knygaDTO) {
	return knygaService.createKnyga(knygaDTO);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/")
    public ResponseEntity<String> editKnyga(@RequestBody KnygaDTO knygaDTO) {
	return knygaService.editKnyga(knygaDTO);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKnyga(@PathVariable Long id) {
	return knygaService.deleteKnyga(id);
    }

}
