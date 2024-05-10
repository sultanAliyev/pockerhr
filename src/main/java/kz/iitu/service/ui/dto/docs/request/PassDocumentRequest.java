package kz.iitu.service.ui.dto.docs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassDocumentRequest {
    private Long documentId;
    private String password;

}
