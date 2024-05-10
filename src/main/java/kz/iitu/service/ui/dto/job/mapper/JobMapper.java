package kz.iitu.service.ui.dto.job.mapper;

import kz.iitu.service.domain.company.repository.CompanyRepository;
import kz.iitu.service.domain.job.model.Job;
import kz.iitu.service.ui.dto.job.response.JobResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JobMapper {

    private final CompanyRepository repository;

    public JobResponse parseCompany(Job job) {
        return new JobResponse(
            job.getId(),
            repository.findById(job.getCompanyId()).get(),
            job.getName(),
            job.getDescription(),
            job.getSalary(),
            job.getCity(),
            job.getType()
        );
    }

    public JobResponse parse(Job job) {
        return new JobResponse(
            job.getId(),
            null,
            job.getName(),
            job.getDescription(),
            job.getSalary(),
            job.getCity(),
            job.getType()
        );
    }

    public List<JobResponse> parseCompany(List<Job> jobs) {
        List<JobResponse> responses = new ArrayList<>();
        for (Job job : jobs) {
            responses.add(parseCompany(job));
        }
        return responses;
    }

    public List<JobResponse> parse(List<Job> jobs) {
        List<JobResponse> responses = new ArrayList<>();
        for (Job job : jobs) {
            responses.add(parse(job));
        }
        return responses;
    }

}
