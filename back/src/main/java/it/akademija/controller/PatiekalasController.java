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

import it.akademija.dto.PatiekalasDTO;
import it.akademija.entity.Patiekalas;
import it.akademija.service.PatiekalasService;

@RestController
@RequestMapping(path = "/api/patiekalas")
public class PatiekalasController {
    
    @Autowired
    private PatiekalasService patiekalasService;
    
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @GetMapping("/all/{id}")
    public Set<Patiekalas> getAllPatiekalasByMaitinimoIstaigaId(@PathVariable Long id) {
	return patiekalasService.getAllPatiekalasByMaitinimoIstaigaId(id);
    }
    
    @Secured({ "ROLE_ADMIN" })
    @GetMapping("/{id}")
    public PatiekalasDTO getPatiekalas(@PathVariable Long id) {
	return patiekalasService.getPatiekalas(id);
    }
    
    @Secured({ "ROLE_ADMIN" })
    @PostMapping("/")
    public ResponseEntity<String> createNewPatiekalas(@RequestBody PatiekalasDTO patiekalasDTO) {
	return patiekalasService.createNewPatiekalas(patiekalasDTO);
    }
    
    @Secured({ "ROLE_ADMIN" })
    @PutMapping("/")
    public ResponseEntity<String> editPatiekalas(@RequestBody PatiekalasDTO patiekalasDTO) {
	return patiekalasService.editPatiekalas(patiekalasDTO);
    }
    
    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatiekalas(@PathVariable Long id) {
	return patiekalasService.deletePatiekalas(id);
    }

}
