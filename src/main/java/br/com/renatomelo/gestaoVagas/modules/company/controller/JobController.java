package br.com.renatomelo.gestaoVagas.modules.company.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatomelo.gestaoVagas.modules.company.dto.CreateJobDTO;
import br.com.renatomelo.gestaoVagas.modules.company.entities.JobEntity;
import br.com.renatomelo.gestaoVagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class JobController {

	@Autowired
	public CreateJobUseCase createJobUseCase;

	@PostMapping("/job")
	@PreAuthorize("hasRole('COMPANY')")
	public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
		Object companyID = request.getAttribute("company_id");
		
		JobEntity jobEntity = JobEntity.builder()
				.benefits(createJobDTO.getBenefits())
				.companyID(UUID.fromString(companyID.toString()))
				.description(createJobDTO.getBenefits())
				.level(createJobDTO.getLevel())
				.build();
		
		return this.createJobUseCase.execute(jobEntity);

	}

}
