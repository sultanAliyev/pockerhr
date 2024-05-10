package kz.iitu.service.ui.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataRequest {

    private String username;
    private String email;
    private String phone;
    private String password;
    private String imageUrl;

}
