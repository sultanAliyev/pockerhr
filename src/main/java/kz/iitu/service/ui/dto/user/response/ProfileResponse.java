package kz.iitu.service.ui.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileResponse {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String avatarUrl;

}
