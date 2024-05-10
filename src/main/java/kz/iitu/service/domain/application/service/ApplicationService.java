package kz.iitu.service.domain.application.service;

import kz.iitu.service.domain.application.model.Application;
import kz.iitu.service.domain.application.model.ApplicationStatus;
import kz.iitu.service.domain.application.repository.ApplicationRepository;
import kz.iitu.service.domain.company.model.Company;
import kz.iitu.service.domain.company.repository.CompanyRepository;
import kz.iitu.service.domain.employee.model.Employee;
import kz.iitu.service.domain.employee.repository.EmployeeRepository;
import kz.iitu.service.domain.job.model.Job;
import kz.iitu.service.domain.job.repository.JobRepository;
import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.ui.dto.application.mapper.ApplicationMapper;
import kz.iitu.service.ui.dto.application.request.AcceptRequest;
import kz.iitu.service.ui.dto.application.response.ApplicationResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;

    private final ApplicationMapper mapper;

    private final JobRepository jobRepository;

    private final CompanyRepository companyRepository;

    private final EmployeeRepository employeeRepository;

    private Job checkHrJob(User user, Long jobId) {
        Optional<Company> companyOptional = companyRepository.findByHrId(user.getId());
        if (companyOptional.isEmpty()) {
            return null;
        }
        Company company = companyOptional.get();
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isEmpty()) {
            return null;
        }
        Job job = jobOptional.get();
        if (!Objects.equals(job.getCompanyId(), company.getId())) {
            return null;
        }
        return job;
    }

    private Pair<Application, Job> checkApplication(User user, Long applicationId) {
        Optional<Application> applicationOptional = repository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            return null;
        }
        Application application = applicationOptional.get();
        Job job = checkHrJob(user, application.getJobId());
        if (job == null) {
            return null;
        }
        return Pair.of(application, job);
    }

    private ResponseEntity<?> setStatus(User user, Long applicationId, ApplicationStatus status) {
        Pair<Application, Job> pair = checkApplication(user, applicationId);
        if (pair == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not found");
        }
        Application application = pair.getFirst();
        application.setStatus(status);
        application.setUpdatedDate(System.currentTimeMillis());
        repository.save(application);
        return ResponseEntity.ok(mapper.parse(application));
    }

    public ResponseEntity<?> request(User user, Long jobId) {
        Optional<Application> applicationOptional = repository.findByJobIdAndUserId(jobId, user.getId());
        if (applicationOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already requested");
        }
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        Long current = System.currentTimeMillis();
        return ResponseEntity.ok(
            mapper.parseUser(
                repository.save(
                    new Application(
                        jobId,
                        user.getId(),
                        current,
                        current,
                        ApplicationStatus.REQUESTED
                    )
                )
            )
        );
    }

    public ResponseEntity<?> findAllForUser(User user) {
        return ResponseEntity.ok(mapper.parseUser(repository.findAllByUserId(user.getId())));
    }

    public ResponseEntity<?> findAllForHr(User user) {
        Optional<Company> companyOptional = companyRepository.findByHrId(user.getId());
        if (companyOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        Company company = companyOptional.get();
        List<Job> jobs = jobRepository.findAllByCompanyId(company.getId());
        List<ApplicationResponse> responses = new ArrayList<>();
        for (Job job : jobs) {
            responses.addAll(mapper.parse(repository.findAllByJobId(job.getId())));
        }
        return ResponseEntity.ok(responses);
    }

    public ResponseEntity<?> findAllForJob(User user, Long id) {
        Job job = checkHrJob(user, id);
        if (job == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        return ResponseEntity.ok(mapper.parseJob(repository.findAllByJobId(id)));
    }

    public ResponseEntity<?> accept(User user, Long applicationId) {
        return setStatus(user, applicationId, ApplicationStatus.ACCEPTED);
    }


    public ResponseEntity<?> reject(User user, Long applicationId) {
        return setStatus(user, applicationId, ApplicationStatus.REJECTED);
    }

    public ResponseEntity<?> finish(User user, Long applicationId, AcceptRequest acceptRequest) {
        Pair<Application, Job> pair = checkApplication(user, applicationId);
        if (pair == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not found");
        }
        Application application = pair.getFirst();
        Job job = pair.getSecond();
        Long current = System.currentTimeMillis();
        application.setStatus(ApplicationStatus.FINISHED);
        application.setUpdatedDate(current);
        repository.save(application);
        Optional<Employee> employeeOptional = employeeRepository.findByUserId(application.getUserId());
        employeeOptional.ifPresent(employeeRepository::delete);
        employeeRepository.save(new Employee(job.getCompanyId(), application.getUserId(), acceptRequest.getTitle(), current, current, acceptRequest.getSalary()));
        return ResponseEntity.ok(mapper.parse(application));
    }

}
