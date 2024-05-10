package kz.iitu.service.configuration.jwt;

import kz.iitu.service.domain.company.model.Company;
import kz.iitu.service.domain.company.repository.CompanyRepository;
import kz.iitu.service.domain.role.model.Role;
import kz.iitu.service.domain.role.repository.RoleRepository;
import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorizationStructure {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CompanyRepository companyRepository;

    public ResponseEntity<?> checkAny(String token, UserAllowedCallBack userAllowedCallBack) {
        Optional<User> userOptional = userRepository.findByToken(token);
        if (userOptional.isPresent()) {
            return userAllowedCallBack.allowed(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong JWT token");
        }
    }

    public ResponseEntity<?> checkHr(String token, UserAllowedCallBack userAllowedCallBack) {
      return checkType(token, userAllowedCallBack, "HR");
    }


    public ResponseEntity<?> checkHrCompany(String token, CompanyAllowedCallBack callBack) {
        Optional<User> userOptional = userRepository.findByToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Role> roleOptional = roleRepository.findByUserId(user.getId());
            if (roleOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong JWT token");
            }
            if (!roleOptional.get().getName().equals("HR")) {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed");
            }
            Optional<Company> companyOptional = companyRepository.findByHrId(user.getId());
            if (companyOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("Company not registered");
            }
            return callBack.allowed(companyOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong JWT token");
        }
    }


    public ResponseEntity<?> checkUser(String token, UserAllowedCallBack userAllowedCallBack) {
      return checkType(token, userAllowedCallBack, "USER");
    }

    public ResponseEntity<?> checkType(String token, UserAllowedCallBack userAllowedCallBack, String role) {
        Optional<User> userOptional = userRepository.findByToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Role> roleOptional = roleRepository.findByUserId(user.getId());
            if (roleOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong JWT token");
            }
            if (!roleOptional.get().getName().equals(role)) {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed");
            }
            return userAllowedCallBack.allowed(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong JWT token");
        }
    }

}