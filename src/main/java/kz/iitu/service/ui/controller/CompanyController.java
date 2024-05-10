package kz.iitu.service.ui.controller;

import kz.iitu.service.configuration.jwt.AuthorizationStructure;
import kz.iitu.service.domain.company.service.CompanyService;
import kz.iitu.service.ui.dto.company.request.CompanyRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static kz.iitu.service.configuration.jwt.AuthorizationStructure.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
@CrossOrigin("*")
public class CompanyController {

    private final AuthorizationStructure authorization;

    private final CompanyService service;


    @PostMapping
    public ResponseEntity<?> registerCompany(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody CompanyRequest companyRequest
    ) throws Exception {
        return authorization.checkHr(token, user -> service.registerCompany(user, companyRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateCompany(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody CompanyRequest companyRequest
    ) throws Exception {
        return authorization.checkHr(token, user -> service.updateCompany(user, companyRequest));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> get(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long companyId
    ) throws Exception {
        return authorization.checkAny(token, user -> service.get(companyId));
    }
}
