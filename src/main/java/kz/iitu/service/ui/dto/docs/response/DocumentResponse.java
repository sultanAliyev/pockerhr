package kz.iitu.service.ui.dto.docs.response;


import kz.iitu.service.ui.dto.user.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponse {
    private Long id;
    private ProfileResponse creator;
    private Long createdDate;
    private Integer stepSize;
    private Integer step;
    private String content;
    private String type;
    private List<RouteResponse> routes;
}
