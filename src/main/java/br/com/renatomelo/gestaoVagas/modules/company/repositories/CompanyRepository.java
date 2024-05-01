package br.com.renatomelo.gestaoVagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatomelo.gestaoVagas.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
	Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
}
