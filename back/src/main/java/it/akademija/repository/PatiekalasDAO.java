package it.akademija.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.akademija.dto.PatiekalasDTO;
import it.akademija.entity.Patiekalas;

public interface PatiekalasDAO extends JpaRepository<Patiekalas, Long>{
    
    @Query("SELECT p FROM Patiekalas p WHERE p.maitinimoIstaiga.id=?1")
    Set<Patiekalas> getAllPatiekalasByMaitinimoIstaigaId(Long id);
    
    @Query("SELECT NEW it.akademija.dto.PatiekalasDTO("
	    + "p.id, "
	    + "p.pavadinimas, "
	    + "p.aprasymas, "
	    + "p.maitinimoIstaiga.id) "
	    + "FROM Patiekalas p WHERE p.id=?1")
    PatiekalasDTO getPatiekalasById(Long id);

}
