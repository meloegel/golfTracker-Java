package mark.golfTracker.services;

import mark.golfTracker.exceptions.ResourceNotFoundException;
import mark.golfTracker.models.Role;
import mark.golfTracker.models.User;
import mark.golfTracker.models.UserRoles;
import mark.golfTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public User findByUserId(long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByUsernameContaining(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Transactional
    @Override
    public void delete(long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userRepository.deleteById(id);
    }


    @Override
    public User findByUsername(String name) {
        User user = userRepository.findByUsername(name.toLowerCase());
        if (user == null) {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return user;
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();
        if (user.getUserId() != 0) {
            userRepository.findById(user.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserId() + " not found!"));
            newUser.setUserId(user.getUserId());
        }

        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());

        newUser.getRoles().clear();
        for (UserRoles ur : user.getRoles()) {
            Role addRole = roleService.findRoleById(ur.getRole().getRoleId());
            newUser.getRoles().add(new UserRoles(newUser, addRole));
        }

        return userRepository.save(newUser);
    }


    @Transactional
    @Override
    public User update(User user, long id) {
        User currentUser = findByUserId(id);
        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername())) {
            if (user.getUsername() != null) {
                currentUser.setUsername(user.getUsername()
                        .toLowerCase());
            }
            if (user.getPassword() != null) {
                currentUser.setPasswordNoEncrypt(user.getPassword());
            }
            if (user.getRoles().size() > 0) {
                currentUser.getRoles().clear();
                for (UserRoles ur : user.getRoles()) {
                    Role addRole = roleService.findRoleById(ur.getRole().getRoleId());
                    currentUser.getRoles().add(new UserRoles(currentUser, addRole));
                }
            }
            return userRepository.save(currentUser);
        } else {
            throw new OAuth2AccessDeniedException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

}
