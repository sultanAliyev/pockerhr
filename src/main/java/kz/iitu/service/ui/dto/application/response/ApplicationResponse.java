package kz.iitu.service.ui.dto.application.response;

import kz.iitu.service.domain.application.model.ApplicationStatus;
import kz.iitu.service.ui.dto.job.response.JobResponse;
import kz.iitu.service.ui.dto.user.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {

    private Long id;
    private Long createdDate;
    private Long updatedDate;
    private ApplicationStatus status;
    private ProfileResponse user;
    private JobResponse job;

}
