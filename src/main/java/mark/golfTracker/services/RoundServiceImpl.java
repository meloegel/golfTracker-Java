package mark.golfTracker.services;

import mark.golfTracker.exceptions.ResourceNotFoundException;
import mark.golfTracker.models.Round;
import mark.golfTracker.models.User;
import mark.golfTracker.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roundService")
public class RoundServiceImpl implements RoundService{

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Round> findAll() {
        List<Round> list = new ArrayList<>();
        roundRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Round findByRoundId(long id) throws ResourceNotFoundException {
        return roundRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Round id " + id + " not found!"));
    }

    @Override
    public List<Round> findByCourseName(String courseName) {
        return roundRepository.findByCourseName(courseName);
    }

    @Override
    public List<Round> searchByCourseName(String courseName) {
        return roundRepository.findByCourseNameContainingIgnoreCase(courseName);
    }

    @Override
    public List<Round> findByUser(User user) {
        return roundRepository.findByUser(user);
    }

    @Transactional
    @Override
    public void delete(long id) throws ResourceNotFoundException  {
        roundRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Round id " + id + " not found!"));
        roundRepository.deleteById(id);
    }

    @Override
    public Round save(Round round, long userId) {
        Round newRound = new Round();
        if (round.getRoundId() !=0){
            roundRepository.findById(round.getRoundId())
                    .orElseThrow(() -> new ResourceNotFoundException("Round id " + round.getRoundId() + " not found!"));
            newRound.setRoundId(round.getRoundId());
        }
        newRound.setDate(round.getDate());
        newRound.setTotalScore(round.getTotalScore());
        newRound.setCourseName(round.getCourseName());
        newRound.setDescription(round.getDescription());

        User userInfo = userService.findByUserId(userId);
        newRound.setUser(userInfo);

        return roundRepository.save(newRound);
    }

    @Override
    public Round update(Round round, long id) {
        Round newRound = findByRoundId(id);
        if (newRound.getCourseName() == null) {
            throw new ResourceNotFoundException("Round with id " + id + " does not exist!");
        }
        if (round.getDate() != null){
            newRound.setDate(round.getDate());
        }
        if (round.getTotalScore() != newRound.getTotalScore()) {
            newRound.setTotalScore(round.getTotalScore());
        }
        if (round.getCourseName() != null) {
            newRound.setCourseName(round.getCourseName());
        }
        if (round.getDescription() != null) {
            newRound.setDescription(round.getDescription());
        }
        return roundRepository.save(newRound);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
    roundRepository.deleteAll();
    }
}
