package mark.golfTracker.repository;

import mark.golfTracker.models.Round;
import mark.golfTracker.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoundRepository extends CrudRepository<Round, Long> {
    List<Round> findByCourseName(String courseName);

    List<Round> findByCourseNameContainingIgnoreCase(String courseName);

    List<Round> findByUser(User user);
}
