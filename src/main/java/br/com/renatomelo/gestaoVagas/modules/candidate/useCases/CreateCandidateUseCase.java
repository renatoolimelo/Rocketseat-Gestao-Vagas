package br.com.renatomelo.gestaoVagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.renatomelo.gestaoVagas.exceptions.UserFoundException;
import br.com.renatomelo.gestaoVagas.modules.candidate.entities.CandidateEntity;
import br.com.renatomelo.gestaoVagas.modules.candidate.repository.CandidateRepository;

@Service
public class CreateCandidateUseCase {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public CandidateEntity execute(CandidateEntity candidateEntity) {
		this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
				.ifPresent((user) -> {
					throw new UserFoundException();
				});
		
		String password = passwordEncoder.encode(candidateEntity.getPassword());
		candidateEntity.setPassword(password);
		return this.candidateRepository.save(candidateEntity);
	}

}
