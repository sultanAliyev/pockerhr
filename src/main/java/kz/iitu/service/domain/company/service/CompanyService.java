package kz.iitu.service.domain.company.service;

import kz.iitu.service.domain.company.model.Company;
import kz.iitu.service.domain.company.repository.CompanyRepository;
import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.ui.dto.company.request.CompanyRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public ResponseEntity<?> registerCompany(User user, CompanyRequest companyRequest) {
        Optional<Company> companyOptional = repository.findByHrId(user.getId());
        if (companyOptional.isEmpty()) {
            return ResponseEntity.ok(
                repository.save(
                    new Company(
                        user.getId(),
                        companyRequest.getName(),
                        companyRequest.getAvatarUrl(),
                        companyRequest.getDescription(),
                        companyRequest.getAddress(),
                        companyRequest.getPhone()
                    )
                )
            );
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Company already exists");
        }
    }

    public ResponseEntity<?> updateCompany(User user, CompanyRequest companyRequest) {
        Optional<Company> companyOptional = repository.findById(companyRequest.getId());
        if (companyOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        Company company = companyOptional.get();
        if (!Objects.equals(company.getHrId(), user.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        company.setName(companyRequest.getName());
        company.setAvatarUrl(companyRequest.getAvatarUrl());
        company.setDescription(companyRequest.getDescription());
        company.setAddress(companyRequest.getAddress());
        company.setPhone(companyRequest.getPhone());
        repository.save(company);
        return ResponseEntity.ok(company);
    }

    public ResponseEntity<?> get(Long companyId) {
        Optional<Company> companyOptional = repository.findById(companyId);
        if (companyOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        } else {
            return ResponseEntity.ok(companyOptional.get());
        }
    }
}
