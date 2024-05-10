package kz.iitu.service.domain.job.service;

import kz.iitu.service.domain.company.model.Company;
import kz.iitu.service.domain.company.repository.CompanyRepository;
import kz.iitu.service.domain.job.model.Job;
import kz.iitu.service.domain.job.repository.JobRepository;
import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.ui.dto.job.mapper.JobMapper;
import kz.iitu.service.ui.dto.job.request.JobRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class JobService {

    private final JobRepository repository;

    private final JobMapper mapper;

    private final CompanyRepository companyRepository;

    public ResponseEntity<?> create(User user, JobRequest jobRequest) {
        Optional<Company> companyOptional = companyRepository.findByHrId(user.getId());
        if (companyOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Company not found");
        }
        Company company = companyOptional.get();
        return ResponseEntity.ok(
            mapper.parseCompany(
                repository.save(
                    new Job(
                        company.getId(),
                        jobRequest.getName(),
                        jobRequest.getDescription(),
                        jobRequest.getSalary(),
                        jobRequest.getCity(),
                        jobRequest.getCity()
                    )
                )
            )
        );
    }

    public ResponseEntity<?> update(User user, JobRequest jobRequest) {
        Optional<Company> companyOptional = companyRepository.findByHrId(user.getId());
        if (companyOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Company not found");
        }
        Company company = companyOptional.get();
        Optional<Job> jobOptional = repository.findById(jobRequest.getId());
        if (jobOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        Job job = jobOptional.get();
        if (!Objects.equals(job.getCompanyId(), company.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        job.setName(jobRequest.getName());
        job.setDescription(jobRequest.getDescription());
        job.setSalary(jobRequest.getSalary());
        job.setCity(jobRequest.getCity());
        job.setType(jobRequest.getType());
        repository.save(job);
        return ResponseEntity.ok(mapper.parseCompany(job));
    }

    public ResponseEntity<?> delete(User user, Long id) {
        Optional<Company> companyOptional = companyRepository.findByHrId(user.getId());
        if (companyOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Company not found");
        }
        Company company = companyOptional.get();
        Optional<Job> jobOptional = repository.findById(id);
        if (jobOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        Job job = jobOptional.get();
        if (!Objects.equals(job.getCompanyId(), company.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        repository.delete(job);
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(mapper.parseCompany(StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList())));
    }

    public ResponseEntity<?> search(String word) {
        return ResponseEntity.ok(mapper.parseCompany(repository.searchByNameContainingIgnoreCase(word)));
    }

    public ResponseEntity<?> byCompany(Long companyId) {
        return ResponseEntity.ok(mapper.parse(repository.findAllByCompanyId(companyId)));
    }
}
