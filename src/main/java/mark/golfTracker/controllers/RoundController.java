package mark.golfTracker.controllers;

import mark.golfTracker.models.Round;
import mark.golfTracker.models.User;
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
@RequestMapping("/rounds")
public class RoundController {

    @Autowired
    private RoundService roundService;

    // Returns a list of all rounds
    // Link: http://localhost:2019/rounds/rounds"
    @GetMapping(value = "/rounds", produces = "application/json")
    public ResponseEntity<?> listAllRounds(){
        List<Round> allRounds = roundService.findAll();
        return new ResponseEntity<>(allRounds, HttpStatus.OK);
    }


    // Returns single round based off id
    // Link: http://localhost:2019/rounds/rounds/4
    @GetMapping(value="/rounds/{roundId}", produces = "application/json")
    public ResponseEntity<?> findByRoundId(@PathVariable Long userId){
        Round round = roundService.findByRoundId(userId);
        return new ResponseEntity<>(round, HttpStatus.OK);
    }
}
