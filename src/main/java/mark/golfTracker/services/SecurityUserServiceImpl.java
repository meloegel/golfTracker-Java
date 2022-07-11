package mark.golfTracker.services;

import mark.golfTracker.exceptions.ResourceNotFoundException;
import mark.golfTracker.models.User;
import mark.golfTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    // Verifies that the user is correct and if so creates the authenticated user
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String uname) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(uname.toLowerCase());
        if (user == null){
            throw new ResourceNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), user.getAuthority());
    }
}
