package kz.iitu.service.ui.dto.company.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

    private Long id;
    private String name;
    private String avatarUrl;
    private String description;
    private String address;
    private String phone;

}
