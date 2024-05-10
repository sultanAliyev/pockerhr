package kz.iitu.service.configuration.jwt;

import kz.iitu.service.domain.company.model.Company;
import kz.iitu.service.domain.user.model.User;
import org.springframework.http.ResponseEntity;

public interface CompanyAllowedCallBack {

    ResponseEntity<?> allowed(Company company);

}