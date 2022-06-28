package it.akademija.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.akademija.dto.KnygaDTO;
import it.akademija.entity.Knyga;

public interface KnygaDAO extends JpaRepository<Knyga, Long>{
    
    @Query("SELECT NEW it.akademija.dto.KnygaDTO(k.id, k.pavadinimas, k.santrauka, k.isbn, k.nuotrauka, k.puslapiai, k.kategorija.id) FROM Knyga k")
    Set<KnygaDTO> getAllKnyga();
    
    @Query("SELECT NEW it.akademija.dto.KnygaDTO(k.id, k.pavadinimas, k.santrauka, k.isbn, k.nuotrauka, k.puslapiai, k.kategorija.id) FROM Knyga k WHERE k.kategorija.id=?1")
    Set<KnygaDTO> getAllKnygaByKategorija(Long kategorijaId);
    
    @Query("SELECT NEW it.akademija.dto.KnygaDTO(k.id, k.pavadinimas, k.santrauka, k.isbn, k.nuotrauka, k.puslapiai, k.kategorija.id) FROM Knyga k WHERE k.id=?1")
    KnygaDTO getKnygaById(Long id);
    
    @Query("SELECT NEW it.akademija.dto.KnygaDTO(k.id, k.pavadinimas, k.santrauka, k.isbn, k.nuotrauka, k.puslapiai, k.kategorija.id) FROM Knyga k WHERE UPPER(k.pavadinimas) LIKE(UPPER(CONCAT('%', ?1, '%')))")
    Set<KnygaDTO> getAllKnygaByPavadinimas(String pavadinimas);

}
