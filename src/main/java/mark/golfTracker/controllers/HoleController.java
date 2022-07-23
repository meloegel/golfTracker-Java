package mark.golfTracker.controllers;

import mark.golfTracker.models.Hole;
import mark.golfTracker.models.Round;
import mark.golfTracker.services.HoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/holes")
public class HoleController {

    @Autowired
    private HoleService holeService;

    // Returns a list of all holes
    // Link: http://localhost:2019/holes/holes"
    @GetMapping(value = "/holes", produces = "application/json")
    public ResponseEntity<?> listAllHoles(){
        List<Hole> allHoles = holeService.findAll();
        return new ResponseEntity<>(allHoles, HttpStatus.OK);
    }

}
