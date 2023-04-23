package coms309RoundTrip.Airplanned.repository;


import coms309RoundTrip.Airplanned.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
