package mark.golfTracker.controllers;

import mark.golfTracker.models.User;
import mark.golfTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Returns a list of all users
    // Link: http://localhost:2019/users/users"
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

    // Returns a list of users whose username contains the given substring
    // Link: http://localhost:2019/users/user/name/like/ci
    @GetMapping(value = "/user/name/like/{userName}", produces = "application/json")
    public ResponseEntity<?> searchForUsername(@PathVariable String userName) {
        List<User> user = userService.findByUsernameContaining(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Given a complete User Object, create a new User record and user role records.
    // http://localhost:2019/users/user
    // @param newuser A complete new user to add including roles. (role must already exist.)
    // @return A location header with the URI to the newly created user and a status of CREATED
    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser) throws URISyntaxException {
        newUser.setUserId(0);
        newUser = userService.save(newUser);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(newUser.getUserId())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newUser, responseHeaders, HttpStatus.CREATED);
    }

    // Given a complete User Object, Given the user id, primary key, is in the User table,
    //  ** Roles are handled through different endpoints **
    // Link: http://localhost:2019/users/user/15
    // @param updateUser - A complete User including
    //           roles to be used to replace the User. Roles must already exist.
    // @param userid     The primary key of the user you wish to replace.
    @PutMapping(value = "/user/{userId}", consumes = "application/json")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User updatedUser, @PathVariable long userId) {
        updatedUser.setUserId(userId);
        userService.save(updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


}
