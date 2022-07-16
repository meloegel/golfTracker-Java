package mark.golfTracker.controllers;

import mark.golfTracker.models.User;
import mark.golfTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Returns a list of all users
    // Link: http://localhost:2019/users/users"
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> listAllUsers(){
        List<User> allUsers = userService.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    // Returns single user based off id
    // Link: http://localhost:2019/users/user/4
    @GetMapping(value="/user/{userId}", produces = "application/json")
    public ResponseEntity<?> findByUserId(@PathVariable Long userId){
        User user = userService.findByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Return a user object based on a given username
    // Link: http://localhost:2019/users/user/name/cinnamon
    @GetMapping(value="/user/name/{userName}", produces = "application/json")
    public ResponseEntity<?> getUserByName(@PathVariable String userName) {
        User user = userService.findByUsername(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
