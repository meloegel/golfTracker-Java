package mark.golfTracker.services;

import mark.golfTracker.models.User;
import java.util.List;


public interface UserService {
    List<User> findAll();

    List<User> findByUsernameContaining(String username);

    User findByUserId(long id);

    User findByUsername(String username);

    void delete(long id);

    User save(User user);

    User update(User user, long id);

    public void deleteAll();
}
