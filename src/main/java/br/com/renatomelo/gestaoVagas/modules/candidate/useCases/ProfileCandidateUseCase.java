package br.com.renatomelo.gestaoVagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.renatomelo.gestaoVagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.renatomelo.gestaoVagas.modules.candidate.entities.CandidateEntity;
import br.com.renatomelo.gestaoVagas.modules.candidate.repository.CandidateRepository;

@Service
public class ProfileCandidateUseCase {

	@Autowired
	private CandidateRepository candidateRepository;

	public ProfileCandidateResponseDTO execute(UUID idCandidate) {
		CandidateEntity candidateEntity = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
			throw new UsernameNotFoundException("User not found");
		});
		
		ProfileCandidateResponseDTO profileCandidateResponseDTO = ProfileCandidateResponseDTO.builder()
				.description(candidateEntity.getDescription())
				.usernme(candidateEntity.getUsername())
				.email(candidateEntity.getEmail())
				.id(candidateEntity.getId())
				.name(candidateEntity.getName())
				.build();
		return profileCandidateResponseDTO;
	}

}
