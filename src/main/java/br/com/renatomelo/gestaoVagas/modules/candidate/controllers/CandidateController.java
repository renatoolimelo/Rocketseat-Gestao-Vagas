package br.com.renatomelo.gestaoVagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatomelo.gestaoVagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.renatomelo.gestaoVagas.modules.candidate.entities.CandidateEntity;
import br.com.renatomelo.gestaoVagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.renatomelo.gestaoVagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CreateCandidateUseCase createCandidateUseCase;

	@Autowired
	private ProfileCandidateUseCase profileCandidateUseCase;

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
		try {
			CandidateEntity result = this.createCandidateUseCase.execute(candidateEntity);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/")
	public ResponseEntity<Object> get(HttpServletRequest request) {

		Object idCandidate = request.getAttribute("candidate_id");

		try {
			ProfileCandidateResponseDTO profile = this.profileCandidateUseCase
					.execute(UUID.fromString(idCandidate.toString()));
			return ResponseEntity.ok().body(profile);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
