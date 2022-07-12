package mark.golfTracker.services;

import mark.golfTracker.models.Round;
import mark.golfTracker.models.User;

import java.util.List;

public interface RoundService {
    List<Round> findAll();

    Round findByRoundId(long id);

    List<Round> findByCourseName(String courseName);

    List<Round> searchByCourseName(String courseName);

    List<Round> findByUser(User user);

    void delete(long id);

    Round save(Round round);

    Round update(Round round, long id);

    public void deleteAll();
}
