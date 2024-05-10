package kz.iitu.service.ui.dto.docs.response;


import kz.iitu.service.ui.dto.user.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteResponse {
    private Long id;
    private ProfileResponse user;
    private Long signatureDate;

}
