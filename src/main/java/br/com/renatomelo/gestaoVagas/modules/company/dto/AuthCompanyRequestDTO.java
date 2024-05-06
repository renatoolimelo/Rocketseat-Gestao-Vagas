package br.com.renatomelo.gestaoVagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyRequestDTO {
	
	private String password;
	private String username;

}
