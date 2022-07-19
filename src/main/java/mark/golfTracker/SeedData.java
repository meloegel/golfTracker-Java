package mark.golfTracker;

import mark.golfTracker.models.Role;
import mark.golfTracker.models.User;
import mark.golfTracker.models.UserRoles;
import mark.golfTracker.services.RoleService;
import mark.golfTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@ConditionalOnProperty(
        prefix = "command.line.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true)
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;
    @Transactional
    @Override
    public void run(String[] args) throws
            Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);



        // admin, data, user 1
        User u1 = new User("admin", "password", "admin@lambdaschool.local");
        u1.getRoles().add(new UserRoles(u1, r1));
        u1.getRoles().add(new UserRoles(u1, r2));
        u1.getRoles().add(new UserRoles(u1, r3));
        userService.save(u1);

        // data, user 2
        User u2 = new User("cinnamon", "1234567", "cinnamon@lambdaschool.local");
        u2.getRoles().add(new UserRoles(u2, r2));
        u2.getRoles().add(new UserRoles(u2, r3));
        userService.save(u2);

        // user 3
        User u3 = new User("barnbarn", "ILuvM4th!", "barnbarn@lambdaschool.local");
        u3.getRoles().add(new UserRoles(u3, r2));
        userService.save(u3);

        // user 4
        User u4 = new User("puttat", "password", "puttat@school.lambda");
        u4.getRoles().add(new UserRoles(u4, r2));
        userService.save(u4);

        // user 5
        User u5 = new User("misskitty", "password", "misskitty@school.lambda");
        u5.getRoles().add(new UserRoles(u5, r2));
        userService.save(u5);

        // user 6
        User u6 = new User("fart", "password", "email@email.com");
        u6.getRoles().add(new UserRoles(u6, r1));
        u6.getRoles().add(new UserRoles(u6, r2));
        u6.getRoles().add(new UserRoles(u6, r3));
        userService.save(u6);


    }
}
