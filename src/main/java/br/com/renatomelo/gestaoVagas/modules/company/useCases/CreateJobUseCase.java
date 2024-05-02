package br.com.renatomelo.gestaoVagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renatomelo.gestaoVagas.modules.company.entities.JobEntity;
import br.com.renatomelo.gestaoVagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {
	
	@Autowired
	private JobRepository jobRepository;
	
	public JobEntity execute(JobEntity jobEntity) {
		return this.jobRepository.save(jobEntity);
	}

}
