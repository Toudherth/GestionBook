package com.example.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet.entity.Livre;
import com.example.projet.exception.LivreNotFoundException;
import com.example.projet.repository.LivreRepository;

@Service
public class LivreService {
    private LivreRepository livreRepository;

    @Autowired
    public LivreService(LivreRepository livreRepository){
        this.livreRepository= livreRepository;
    }

    // All books
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    // get book by titre or auteur 
    public List<Livre> getByTitleOrAuteur(String titre, String auteur) {
        return livreRepository.findByTitreOrAuteur(titre, auteur);
    }

    public Livre addLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    // Update book 
    public Livre updateLivre(Long livreId, Livre updatedLivre) {
        Livre livre = livreRepository.findById(livreId)
                .orElseThrow(() -> new LivreNotFoundException("Livre introuvable avec l'ID : " + livreId));
        
        // Vérifier et mettre à jour chaque attribut individuellement
        if (updatedLivre.getTitre() != null) {
            livre.setTitre(updatedLivre.getTitre());
        }
        
        if (updatedLivre.getAuteur() != null) {
            livre.setAuteur(updatedLivre.getAuteur());
        }
        
        if (updatedLivre.getResume() != null) {
            livre.setResume(updatedLivre.getResume());
        }
        
        if (updatedLivre.getDatepublication() != null) {
            livre.setDatepublication(updatedLivre.getDatepublication());
        }
    
        return livreRepository.save(livre);
    }
    

    // Delete book
    public void deleteLivre(Long livreId) {
        Livre livre = livreRepository.findById(livreId)
                .orElseThrow(() -> new LivreNotFoundException("Livre introuvable avec l'ID : " + livreId));
    
        livreRepository.delete(livre);
    }
    
}
