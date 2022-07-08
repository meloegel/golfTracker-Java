package mark.golfTracker.repository;

import mark.golfTracker.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByUserId(long userId);

    List<User> findByUsernameContainingIgnoreCase(String name);
}
