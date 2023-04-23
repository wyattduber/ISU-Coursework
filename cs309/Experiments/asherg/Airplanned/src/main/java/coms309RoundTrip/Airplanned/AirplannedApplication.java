package coms309RoundTrip.Airplanned;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AirplannedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirplannedApplication.class, args);
	}

}
