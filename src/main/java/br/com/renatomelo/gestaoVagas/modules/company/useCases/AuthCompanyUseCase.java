package br.com.renatomelo.gestaoVagas.modules.company.useCases;

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

import br.com.renatomelo.gestaoVagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.renatomelo.gestaoVagas.modules.company.dto.AuthCompanyRequestDTO;
import br.com.renatomelo.gestaoVagas.modules.company.entities.CompanyEntity;
import br.com.renatomelo.gestaoVagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

	@Value("${security.token.secret}")
	private String secretKey;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthCandidateResponseDTO execute(AuthCompanyRequestDTO authCompanyDTO) throws AuthenticationException {
		CompanyEntity companyEntity = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("Username/password incorrect");
				});

		boolean passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(),
				companyEntity.getPassword());

		if (!passwordMatches) {
			throw new AuthenticationException();
		}
		
		Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String token = JWT.create().withIssuer("javagas")
				.withSubject(companyEntity.getId().toString())
				.withExpiresAt(expiresIn)
				.withClaim("roles", Arrays.asList("COMPANY"))
				.sign(algorithm);
		
		AuthCandidateResponseDTO authCandidateResponseDTO = AuthCandidateResponseDTO.builder()
				.access_token(token)
				.expires_in(expiresIn.toEpochMilli())
				.build();
		
		return authCandidateResponseDTO;

	}

}
