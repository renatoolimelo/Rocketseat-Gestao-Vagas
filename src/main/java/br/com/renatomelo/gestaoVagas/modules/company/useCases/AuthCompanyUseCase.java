package br.com.renatomelo.gestaoVagas.modules.company.useCases;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.renatomelo.gestaoVagas.modules.company.dto.AuthCompanyDTO;
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

	public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
		CompanyEntity companyEntity = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("Username/password incorrect");
				});

		boolean passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(),
				companyEntity.getPassword());

		if (!passwordMatches) {
			throw new AuthenticationException();
		}

		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String token = JWT.create().withIssuer("javagas").withSubject(companyEntity.getId().toString()).sign(algorithm);
		return token;
	}

}
