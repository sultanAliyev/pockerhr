package kz.iitu.service.ui.controller;

import kz.iitu.service.configuration.jwt.AuthorizationStructure;
import kz.iitu.service.domain.employee.service.EmployeeService;
import kz.iitu.service.ui.dto.employee.request.EmployeeRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.iitu.service.configuration.jwt.AuthorizationStructure.AUTHORIZATION_HEADER;


@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

    private final AuthorizationStructure authorization;

    private final EmployeeService service;

    @GetMapping("/user")
    public ResponseEntity<?> getUserPosition(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkUser(token, service::getUserPosition);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkHrCompany(token, service::findAll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long id
    ) throws Exception {
        return authorization.checkHrCompany(token, company -> service.delete(company, id));
    }

    @PutMapping
    public ResponseEntity<?> update(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody EmployeeRequest employeeRequest
    ) throws Exception {
        return authorization.checkHrCompany(token, company -> service.update(company, employeeRequest));
    }
}
