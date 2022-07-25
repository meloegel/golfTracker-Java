package mark.golfTracker.controllers;

import mark.golfTracker.models.Hole;
import mark.golfTracker.models.Round;
import mark.golfTracker.models.User;
import mark.golfTracker.services.HoleService;
import mark.golfTracker.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/holes")
public class HoleController {

    @Autowired
    private HoleService holeService;

    @Autowired
    private RoundService roundService;

    // Returns a list of all holes
    // Link: http://localhost:2019/holes/holes"
    @GetMapping(value = "/holes", produces = "application/json")
    public ResponseEntity<?> listAllHoles(){
        List<Hole> allHoles = holeService.findAll();
        return new ResponseEntity<>(allHoles, HttpStatus.OK);
    }

    // Returns single hole based off id
    // Link: http://localhost:2019/holes/holes/4
    @GetMapping(value="/holes/{holeId}", produces = "application/json")
    public ResponseEntity<?> findByHoleId(@PathVariable Long holeId){
        Hole hole = holeService.findByHoleId(holeId);
        return new ResponseEntity<>(hole, HttpStatus.OK);
    }

    // Return a list of Holes based on a given par
    // Link: http://localhost:2019/holes/holes/par/3
    @GetMapping(value="/holes/holes/par/{par}", produces = "application/json")
    public ResponseEntity<?> getHolesByPar(@PathVariable int par) {
        List<Hole> holeList = holeService.findByPar(par);
        return new ResponseEntity<>(holeList, HttpStatus.OK);
    }

    // Return a list of all Holes for a Round
    // Link: http://localhost:2019/holes/holes/round/3
    @GetMapping(value="/holes/holes/round/{roundId}", produces = "application/json")
    public ResponseEntity<?> getHolesByRound(@PathVariable int roundId) {
        Round round = roundService.findByRoundId(roundId);
        List<Hole> holeList = holeService.findByRound(round);
        return new ResponseEntity<>(holeList, HttpStatus.OK);
    }

}
