package kz.iitu.service.ui.controller;

import kz.iitu.service.domain.user.service.UserService;
import kz.iitu.service.ui.dto.user.request.LoginRequest;
import kz.iitu.service.ui.dto.user.request.UserDataRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authorization")
@AllArgsConstructor
public class AuthorizationController {

    private final UserService service;

    @PostMapping("/login")
    ResponseEntity<?> login(LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    @PostMapping("/register/hr")
    ResponseEntity<?> registerHr(UserDataRequest userDataRequest) {
        return service.registerHr(userDataRequest);
    }

    @PostMapping("/register/user")
    ResponseEntity<?> registerUser(UserDataRequest userDataRequest) {
        return service.registerUser(userDataRequest);
    }

}
