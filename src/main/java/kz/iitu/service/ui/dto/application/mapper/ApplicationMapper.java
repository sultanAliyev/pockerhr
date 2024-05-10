package kz.iitu.service.ui.dto.application.mapper;

import kz.iitu.service.domain.application.model.Application;
import kz.iitu.service.domain.job.repository.JobRepository;
import kz.iitu.service.domain.user.repository.UserRepository;
import kz.iitu.service.ui.dto.application.response.ApplicationResponse;
import kz.iitu.service.ui.dto.job.mapper.JobMapper;
import kz.iitu.service.ui.dto.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ApplicationMapper {

    private final UserRepository userRepository;

    private final JobRepository jobRepository;

    private final JobMapper jobMapper;

    private final UserMapper userMapper;

    public ApplicationResponse parseJob(Application application) {
        return new ApplicationResponse(
            application.getId(),
            application.getCreatedDate(),
            application.getUpdatedDate(),
            application.getStatus(),
            userMapper.parse(userRepository.findById(application.getUserId()).get()),
            null
        );
    }

    public ApplicationResponse parse(Application application) {
        return new ApplicationResponse(
            application.getId(),
            application.getCreatedDate(),
            application.getUpdatedDate(),
            application.getStatus(),
            userMapper.parse(userRepository.findById(application.getUserId()).get()),
            jobMapper.parseCompany(jobRepository.findById(application.getJobId()).get())
        );
    }

    public ApplicationResponse parseUser(Application application) {
        return new ApplicationResponse(
            application.getId(),
            application.getCreatedDate(),
            application.getUpdatedDate(),
            application.getStatus(),
            null,
            jobMapper.parseCompany(jobRepository.findById(application.getJobId()).get())
        );
    }

    public List<ApplicationResponse> parseUser(List<Application> applications) {
        List<ApplicationResponse> responses = new ArrayList<>();
        for (Application application : applications) {
            responses.add(parseUser(application));
        }
        return responses;
    }

    public List<ApplicationResponse> parseJob(List<Application> applications) {
        List<ApplicationResponse> responses = new ArrayList<>();
        for (Application application : applications) {
            responses.add(parseJob(application));
        }
        return responses;
    }

    public List<ApplicationResponse> parse(List<Application> applications) {
        List<ApplicationResponse> responses = new ArrayList<>();
        for (Application application : applications) {
            responses.add(parse(application));
        }
        return responses;
    }

}
