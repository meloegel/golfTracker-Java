package mark.golfTracker.services;

import mark.golfTracker.models.Hole;
import mark.golfTracker.models.Round;

import java.util.List;

public interface HoleService {
    List<Hole> findAll();

    Hole findByHoleId(long id);

    List<Hole> findByPar(long par);

    List<Hole> findByScore(long score);

    List<Hole> findByPutts(long putts);

    List<Hole> findByRound(Round round);

    public void deleteAll();
}
