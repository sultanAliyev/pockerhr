package kz.iitu.service.domain.user.service;

import kz.iitu.service.configuration.jwt.TokenUtils;
import kz.iitu.service.domain.role.model.Role;
import kz.iitu.service.domain.role.repository.RoleRepository;
import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.domain.user.repository.UserRepository;
import kz.iitu.service.ui.dto.user.mapper.UserMapper;
import kz.iitu.service.ui.dto.user.request.LoginRequest;
import kz.iitu.service.ui.dto.user.request.UserDataRequest;
import kz.iitu.service.ui.dto.user.response.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    private User register(UserDataRequest userDataRequest) {
        Optional<User> userOptional = repository.findByEmail(userDataRequest.getEmail());
        if (userOptional.isPresent()) {
            return null;
        }
        return repository.save(new User(userDataRequest.getUsername(), userDataRequest.getEmail(), userDataRequest.getPhone(), userDataRequest.getImageUrl(), userDataRequest.getPassword(), generateToken(userDataRequest.getEmail(), userDataRequest.getPassword())));
    }

    private Role getRole(User user) {
        return roleRepository.findByUserId(user.getId()).get();
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<User> userOptional = repository.findByEmail(loginRequest.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Email not registered");
        }
        User user = userOptional.get();
        if (user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong password");
        }
        user.setToken(generateToken(user.getEmail(), user.getPassword()));
        repository.save(user);
        return ResponseEntity.ok(new TokenResponse(user.getToken(), getRole(user).getName()));
    }

    public ResponseEntity<?> registerHr(UserDataRequest userDataRequest) {
        User user = register(userDataRequest);
        if (user == null) {
            return ResponseEntity.badRequest().body("Email registered!");
        }
        roleRepository.save(new Role(user.getId(), "HR"));
        return ResponseEntity.ok(new TokenResponse(user.getToken(), "HR"));
    }

    public ResponseEntity<?> registerUser(UserDataRequest userDataRequest) {
        User user = register(userDataRequest);
        if (user == null) {
            return ResponseEntity.badRequest().body("Email registered!");
        }
        roleRepository.save(new Role(user.getId(), "USER"));
        return ResponseEntity.ok(new TokenResponse(user.getToken(), "USER"));
    }

    public ResponseEntity<?> getProfile(User user) {
        return ResponseEntity.ok(userMapper.parse(user));
    }

    public ResponseEntity<?> changeProfile(User user, UserDataRequest userDataRequest) {
        user.setPhone(userDataRequest.getPhone());
        user.setPassword(userDataRequest.getPassword());
        user.setAvatarUrl(userDataRequest.getImageUrl());
        user.setUsername(userDataRequest.getUsername());
        user.setToken(generateToken(userDataRequest.getEmail(), userDataRequest.getPassword()));
        repository.save(user);
        return ResponseEntity.ok(new TokenResponse(user.getToken(), getRole(user).getName()));
    }


    private String generateToken(String email, String password) {
        return TokenUtils.generateUserToken(email, password);
    }
}
