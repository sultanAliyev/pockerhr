package kz.iitu.service.ui.dto.job.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {

    private Long id;
    private String name;
    private String description;
    private String salary;
    private String city;
    private String type;

}
