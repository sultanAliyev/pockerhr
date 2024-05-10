package kz.iitu.service.domain.role.repository;

import kz.iitu.service.domain.role.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByUserId(Long userId);

}
