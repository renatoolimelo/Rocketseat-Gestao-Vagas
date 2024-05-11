package br.com.renatomelo.gestaoVagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.renatomelo.gestaoVagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.renatomelo.gestaoVagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.renatomelo.gestaoVagas.modules.candidate.entities.CandidateEntity;
import br.com.renatomelo.gestaoVagas.modules.candidate.repository.CandidateRepository;

@Service
public class AuthCandidateUseCase {

	@Value("${security.token.secret.candidate}")
	private String secretKey;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateDTO) throws AuthenticationException {
		CandidateEntity candidateEntity = this.candidateRepository.findByUsername(authCandidateDTO.username())
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("Username/password incorrect");
				});

		boolean passwordMatches = this.passwordEncoder.matches(authCandidateDTO.password(),
				candidateEntity.getPassword());

		if (!passwordMatches) {
			throw new AuthenticationException();
		}
		
		Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String token = JWT.create().withIssuer("javagas").withExpiresAt(expiresIn)
				.withSubject(candidateEntity.getId().toString())
				.withClaim("roles", Arrays.asList("CANDIDATE")).sign(algorithm);

		AuthCandidateResponseDTO authCandidateResponseDTO = AuthCandidateResponseDTO.builder()
				.access_token(token)
				.expires_in(expiresIn.toEpochMilli())
				.build();

		return authCandidateResponseDTO;
	}

}
