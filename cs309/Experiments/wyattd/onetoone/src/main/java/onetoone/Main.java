package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import onetoone.Trips.Trip;
import onetoone.Trips.TripRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

/**
 *
 * @author Vivek Bengre
 *
 */

@SpringBootApplication
@EnableJpaRepositories
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines
    /**
     *
     * @param userRepository repository for the User entity
     * @param laptopRepository repository for the Laptop entity
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in User.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, TripRepository laptopRepository) {
        return args -> {
            User user1 = new User("John", "john@somemail.com", "abc123");
            User user2 = new User("Jane", "jane@somemail.com", "password");
            User user3 = new User("Justin", "justin@somemail.com", "GoPackGo1");
            Trip laptop1 = new Trip( 2.5, 4, 8, "Lenovo", 300);
            Trip laptop2 = new Trip( 4.1, 8, 16, "Hp", 800);
            Trip laptop3 = new Trip( 3.5, 32, 32, "Dell", 2300);
            user1.setLaptop(laptop1);
            user2.setLaptop(laptop2);
            user3.setLaptop(laptop3);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        };
    }

}
