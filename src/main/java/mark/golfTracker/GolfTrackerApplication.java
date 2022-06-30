package mark.golfTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GolfTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GolfTrackerApplication.class, args);
	}

}
