package mark.golfTracker.controllers;

import mark.golfTracker.models.Role;
import mark.golfTracker.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // The Role referenced by the given primary key
    // Link: http://localhost:2019/roles/role/3
    // @param roleId - The primary key (long) of the role you seek
    @GetMapping(value = "/role/{roleId}", produces = "application/json")
    public ResponseEntity<?> getRoleById(@PathVariable Long roleId) {
        Role role = roleService.findRoleById(roleId);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
