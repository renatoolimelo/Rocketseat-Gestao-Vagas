package br.com.renatomelo.gestaoVagas.modules.candidate.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatomelo.gestaoVagas.modules.candidate.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
	Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

}
