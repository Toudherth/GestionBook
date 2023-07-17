package com.example.projet;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.projet.entity.Livre;
import com.example.projet.repository.LivreRepository;

@SpringBootApplication
public class ProjetApplication implements CommandLineRunner{

	private LivreRepository livreRepository;
    @Autowired
    public ProjetApplication(LivreRepository livreRepository){
        this.livreRepository= livreRepository;
    }


	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}
  

    public void run(String... args) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("1997-06-26");
        Date date2 = dateFormat.parse("1954-07-29");
        Date date3 = dateFormat.parse("2020-08-01");
        Date date4 = dateFormat.parse("2002-12-01");
        Date date5 = dateFormat.parse("1982-04-12");
        Date date6 = dateFormat.parse("2015-11-18");
    
        livreRepository.save(new Livre(null, "Harry Potter à l'école des sorciers", "J.K. Rowling", "Les aventures d'un jeune sorcier et de ses amis à Poudlard.", date1));
        livreRepository.save(new Livre(null, "Le Seigneur des Anneaux", "J.R.R. Tolkien", "Une grande quête épique dans un monde fantastique.", date2));
        livreRepository.save(new Livre(null, "1984", "George Orwell", "Un roman dystopique sur la surveillance et la manipulation du pouvoir.", date3));
        livreRepository.save(new Livre(null, "Le Petit Prince", "Antoine de Saint-Exupéry", "Un conte philosophique sur l'amitié et l'importance de l'imagination.", date4));
        livreRepository.save(new Livre(null, "To Kill a Mockingbird", "Harper Lee", "Un roman sur l'injustice et le racisme dans une petite ville américaine.", date5));
        livreRepository.save(new Livre(null, "Pride and Prejudice", "Jane Austen", "Une histoire d'amour et de préjugés dans la société anglaise du XIXe siècle.", date6));
    }
    

}
