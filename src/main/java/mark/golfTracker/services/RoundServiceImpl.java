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
    public Round save(Round round) {
        return null;
    }

    @Override
    public Round update(Round round, long id) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
    roundRepository.deleteAll();
    }
}
