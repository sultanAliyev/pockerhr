package kz.iitu.service.ui.dto.job.response;

import kz.iitu.service.domain.company.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {

    private Long id;
    private Company company;
    private String name;
    private String description;
    private String salary;
    private String city;
    private String type;

}
