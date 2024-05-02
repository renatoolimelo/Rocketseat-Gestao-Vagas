package br.com.renatomelo.gestaoVagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatomelo.gestaoVagas.modules.company.entities.CompanyEntity;
import br.com.renatomelo.gestaoVagas.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	public CreateCompanyUseCase createCompanyUseCase;

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
		try {
			CompanyEntity result = this.createCompanyUseCase.execute(companyEntity);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
