package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan()
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(KorisnikRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Korisnik("Jack", "Bauer","jBauer","1234",0,0,0));
            repository.save(new Korisnik("Chloe", "O'Brian","coBrian","1234",0,0,0));
            repository.save(new Korisnik("Kim", "Bauer","kBauer","1234",0,0,0));


            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Korisnik korisnik : repository.findAll()) {
                log.info(korisnik.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(korisnik -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(korisnik.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}