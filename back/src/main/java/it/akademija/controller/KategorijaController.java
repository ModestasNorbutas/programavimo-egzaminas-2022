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

import it.akademija.dto.KategorijaDTO;
import it.akademija.service.KategorijaService;

@RestController
@RequestMapping(path = "/api/kategorija")
public class KategorijaController {
    
    @Autowired
    private KategorijaService kategorijaService;
    
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/all")
    public Set<KategorijaDTO> getAllKategorija() {
	return kategorijaService.getAllKategorija();
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public KategorijaDTO getKategorija(@PathVariable Long id) {
	return kategorijaService.getKategorija(id);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/")
    public ResponseEntity<String> createKategorija(@RequestBody KategorijaDTO kategorijaDTO) {
	return kategorijaService.createKategorija(kategorijaDTO);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/")
    public ResponseEntity<String> editKategorija(@RequestBody KategorijaDTO kategorijaDTO) {
	return kategorijaService.editKategorija(kategorijaDTO);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKategorija(@PathVariable Long id) {
	return kategorijaService.deleteKategorija(id);
    }

}
