package br.com.renatomelo.gestaoVagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatomelo.gestaoVagas.modules.company.entities.JobEntity;
import br.com.renatomelo.gestaoVagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	public CreateJobUseCase createJobUseCase;
	
	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity){
		try {
			JobEntity result = this.createJobUseCase.execute(jobEntity);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
