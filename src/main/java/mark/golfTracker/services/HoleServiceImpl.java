package mark.golfTracker.services;

import mark.golfTracker.exceptions.ResourceNotFoundException;
import mark.golfTracker.models.Hole;
import mark.golfTracker.models.Round;
import mark.golfTracker.repository.HoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "holeService")
public class HoleServiceImpl implements HoleService {

    @Autowired
    private HoleRepository holeRepository;

    @Override
    public List<Hole> findAll() {
        List<Hole> list = new ArrayList<>();
        holeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Hole findByHoleId(long id) throws ResourceNotFoundException {
        return holeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hole id " + id + " not found!"));
    }

    @Override
    public List<Hole> findByPar(long par) {
        return holeRepository.findByPar(par);
    }

    @Override
    public List<Hole> findByRound(Round round) {
        return holeRepository.findByRound(round);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
        holeRepository.deleteAll();
    }
}
