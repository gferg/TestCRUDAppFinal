package app;

import app.domain.User;
import app.domain.UserStatus;
import app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    @Bean
    public CommandLineRunner loadData(UserRepository repository) {
        return (args) -> {

            repository.save(new User("Jack", "21", UserStatus.TRUE));
            repository.save(new User("Hanna", "27", UserStatus.FALSE));
            repository.save(new User("Bred", "18", UserStatus.TRUE));
            repository.save(new User("Michelle", "25", UserStatus.FALSE));
            repository.save(new User("Ksenya", "22", UserStatus.TRUE));
            repository.save(new User("Amanda", "24", UserStatus.TRUE));
            repository.save(new User("Frank", "27", UserStatus.FALSE));
            repository.save(new User("Kimberly", "26", UserStatus.TRUE));
            repository.save(new User("James", "28", UserStatus.FALSE));
            repository.save(new User("Donna", "23", UserStatus.TRUE));

            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");


            User user = repository.findOne(3);
            log.info("User found with findOne(3):");
            log.info("--------------------------------");
            log.info(user.toString());
            log.info("");

            log.info("User found with findByNameStartsWithIgnoreCase('Jack'):");
            log.info("--------------------------------------------");
            for (User jack : repository
                    .findByNameStartsWithIgnoreCase("Jack")) {
                log.info(jack.toString());
            }
            log.info("");
        };
    }
}
