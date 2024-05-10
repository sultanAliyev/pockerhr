package kz.iitu.service.ui.dto.employee.response;

import kz.iitu.service.ui.dto.user.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String title;
    private String salary;
    private Long createdDate;
    private Long updatedDate;
    private ProfileResponse user;

}
