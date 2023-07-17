package com.example.projet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projet.entity.Livre;
import com.example.projet.exception.LivreException;
import com.example.projet.exception.LivreNotFoundException;
import com.example.projet.exception.LivresNotFoundException;
import com.example.projet.service.LivreService;

@RestController
@RequestMapping("/livres")
@CrossOrigin("http://localhost:8080/")
public class LivreController {

    private LivreService livreService;

    @Autowired
    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    // get all 
    @GetMapping
    public ResponseEntity<List<Livre>> getAll() {
        try {
            List<Livre> livres = livreService.getAllLivres();
            return ResponseEntity.ok(livres);
        } catch (LivresNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // add 
    @PostMapping
    public ResponseEntity<?> addLivre(@RequestBody Livre livre) {
        try {
            Livre livretoadd = livreService.addLivre(livre);
            return ResponseEntity.ok(livretoadd);
        } catch (LivreException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur interne s'est produite");
        }
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable("id") Long livreId, @RequestBody Livre updatedlivre) {
        try {
            Livre livre = livreService.updateLivre(livreId, updatedlivre);
            return ResponseEntity.ok(livre);
        } catch (LivreNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //delete 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable("id") Long livreId) {
        try {
            livreService.deleteLivre(livreId);
            return ResponseEntity.noContent().build();
        } catch (LivreNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }  

    // search
    @GetMapping("/recherche")
    public ResponseEntity<List<Livre>> getByTitle(@RequestParam(required = false) String titre,
                                                  @RequestParam(required = false) String auteur) {
        try {
            List<Livre> livres = livreService.getByTitleOrAuteur(titre, auteur);
            return ResponseEntity.ok(livres);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
