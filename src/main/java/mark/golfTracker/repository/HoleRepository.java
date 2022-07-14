package mark.golfTracker.repository;

import mark.golfTracker.models.Hole;
import mark.golfTracker.models.Round;
import mark.golfTracker.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface HoleRepository extends CrudRepository<Hole, Long> {

    List<Hole> findByPar(long par);

    List<Hole> findByRound(Round round);

    List<Hole> findByScore(long score);

    List<Hole> findByPutts(long putts);
}
