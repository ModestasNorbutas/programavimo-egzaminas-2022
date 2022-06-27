package it.akademija.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.akademija.entity.MaitinimoIstaiga;

public interface MaitinimoIstaigaDAO extends JpaRepository<MaitinimoIstaiga, Long>{
    
    @Query("SELECT m FROM MaitinimoIstaiga m")
    Set<MaitinimoIstaiga> getAllMaitinimoIstaiga();
    
    boolean existsByPavadinimas(String pavadinimas);
    
}
