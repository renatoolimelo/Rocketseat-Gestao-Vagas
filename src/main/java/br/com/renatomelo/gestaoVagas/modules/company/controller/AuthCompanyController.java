package br.com.renatomelo.gestaoVagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatomelo.gestaoVagas.modules.company.dto.AuthCompanyRequestDTO;
import br.com.renatomelo.gestaoVagas.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

	@Autowired
	private AuthCompanyUseCase authCompanyUseCase;

	@PostMapping("/auth")
	public ResponseEntity<Object> auth(@RequestBody AuthCompanyRequestDTO authCompanyDTO) {
		try {
			String result = this.authCompanyUseCase.execute(authCompanyDTO);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
}
