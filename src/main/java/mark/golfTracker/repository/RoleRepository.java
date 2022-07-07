package mark.golfTracker.repository;

import mark.golfTracker.models.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByNameIgnoreCase(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE roles SET name = :name, lastModifiedBy = :username, lastModifiedDate = CURRENT_TIMESTAMP WHERE roleId = :roleId", nativeQuery = true)
    void updateRoleName(String username, long roleId, String name);

}
