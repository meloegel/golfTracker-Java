package mark.golfTracker.repository;

import mark.golfTracker.models.Hole;
import mark.golfTracker.models.Round;
import mark.golfTracker.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface HoleRepository extends CrudRepository<User, Long> {
    Hole findByHoleId(long holdId);

    List<Hole> findByRound(Round round);
}
