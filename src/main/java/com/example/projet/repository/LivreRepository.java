package com.example.projet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projet.entity.Livre;


public interface LivreRepository extends JpaRepository<Livre, Long> {
    
    List<Livre> findByTitreOrAuteur(String titre, String auteur);
}