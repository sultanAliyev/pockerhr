package kz.iitu.service.ui.dto.docs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocumentRequest {
    private String content;
    private String type;
    private List<Long> route;
}
