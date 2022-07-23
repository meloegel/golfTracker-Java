package mark.golfTracker.controllers;

import mark.golfTracker.models.Role;
import mark.golfTracker.models.Round;
import mark.golfTracker.models.User;
import mark.golfTracker.services.RoundService;
import mark.golfTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/rounds")
public class RoundController {

    @Autowired
    private RoundService roundService;

    @Autowired
    private UserService userService;

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
    public ResponseEntity<?> findByRoundId(@PathVariable Long roundId){
        Round round = roundService.findByRoundId(roundId);
        return new ResponseEntity<>(round, HttpStatus.OK);
    }

    // Return a list of rounds based on a given courseName
    // Link: http://localhost:2019/rounds/rounds/courseName/sycamore_hills
    @GetMapping(value="/rounds/courseName/{courseName}", produces = "application/json")
    public ResponseEntity<?> getRoundsByCourseName(@PathVariable String courseName) {
        List<Round> roundList = roundService.findByCourseName(courseName);
        return new ResponseEntity<>(roundList, HttpStatus.OK);
    }

    // Return a list of rounds based on a given courseName or part of courseName
    // Link: http://localhost:2019/rounds/rounds/search/courseName/sycam
    @GetMapping(value="/rounds/courseName/search/{courseName}", produces = "application/json")
    public ResponseEntity<?> searchRoundsByCourseName(@PathVariable String courseName) {
        List<Round> roundList = roundService.searchByCourseName(courseName);
        return new ResponseEntity<>(roundList, HttpStatus.OK);
    }

    // Return a list of rounds based on a given user
    // Link: http://localhost:2019/rounds/rounds/user/3
    @GetMapping(value="/rounds/user/{userId}", produces = "application/json")
    public ResponseEntity<?> getRoundsByUser(@PathVariable int userId) {
        User user = userService.findByUserId(userId);
        List<Round> roundList = roundService.findByUser(user);
        return new ResponseEntity<>(roundList, HttpStatus.OK);
    }

    // Given a complete round object, create a new Round record
    // Link: http://localhost:2019/rounds/rounds/2
    // @param newRound - A complete new Round object
    @PostMapping(value = "/rounds/{userId}", consumes = "application/json")
    public ResponseEntity<?> addNewRound(@Valid @RequestBody Round newRound, @PathVariable Long userId) {
        newRound.setRoundId(0);
        roundService.save(newRound, userId);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    // update Round given a complete Round Object
    // Link: http://localhost:2019/rounds/rounds/15
    // @param updatedRound - A complete Round including
    // @param roundId  - The primary key of the user you wish to replace.
    @PutMapping(value = "/rounds/{roundId}", consumes = "application/json")
    public ResponseEntity<?> updateRound(@Valid @RequestBody Round updatedRound, @PathVariable long roundId) {
        updatedRound.setRoundId(roundId);
        roundService.save(updatedRound, updatedRound.getRoundId());
        return new ResponseEntity<>(updatedRound, HttpStatus.OK);
    }

    // Deletes a given round
    // Link: http://localhost:2019/rounds/rounds/4
    // @param id - The primary key of the round you wish to delete
    @DeleteMapping(value = "/round/{roundId}")
    public ResponseEntity<?> deleteByRoundId(@PathVariable long roundId) {
        roundService.delete(roundId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
