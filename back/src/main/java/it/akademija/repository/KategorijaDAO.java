package it.akademija.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.akademija.dto.KategorijaDTO;
import it.akademija.entity.Kategorija;

public interface KategorijaDAO extends JpaRepository<Kategorija, Long>{
    
    @Query("SELECT NEW it.akademija.dto.KategorijaDTO(k.id, k.pavadinimas) FROM Kategorija k")
    Set<KategorijaDTO> getAllKategorija();
    
    @Query("SELECT NEW it.akademija.dto.KategorijaDTO(k.id, k.pavadinimas) FROM Kategorija k WHERE k.id=?1")
    KategorijaDTO getKategorija(Long id);
    
    @Query("SELECT k FROM Kategorija k WHERE k.pavadinimas=?1")
    Optional<Kategorija> findByPavadinimas(String pavadinimas);

}
