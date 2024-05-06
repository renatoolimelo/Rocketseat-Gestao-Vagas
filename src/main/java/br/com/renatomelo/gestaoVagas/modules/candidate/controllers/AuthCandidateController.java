package br.com.renatomelo.gestaoVagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatomelo.gestaoVagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.renatomelo.gestaoVagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.renatomelo.gestaoVagas.modules.candidate.useCases.AuthCandidateUseCase;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

	@Autowired
	private AuthCandidateUseCase authCandidateUseCase;

	@PostMapping("/auth")
	public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateDTO) {
		try {
			AuthCandidateResponseDTO result = this.authCandidateUseCase.execute(authCandidateDTO);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

}
