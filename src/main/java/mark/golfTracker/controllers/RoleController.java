package mark.golfTracker.controllers;

import mark.golfTracker.models.Role;
import mark.golfTracker.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    // List of all roles
    // Link: http://localhost:2019/roles/roles
    @GetMapping(value = "/roles", produces = "application/json")
    public ResponseEntity<?> listRoles() {
        List<Role> allRoles = roleService.findAll();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

}
