package kz.iitu.service.domain.role.service;

import kz.iitu.service.domain.role.model.Role;
import kz.iitu.service.domain.role.repository.RoleRepository;
import kz.iitu.service.domain.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public ResponseEntity<?> get(User user) {
        Optional<Role> roleOptional = repository.findByUserId(user.getId());
        return roleOptional.<ResponseEntity<?>>map(role -> ResponseEntity.ok(role.getName())).orElseGet(() -> ResponseEntity.badRequest().body("Emtpy"));
    }

}
