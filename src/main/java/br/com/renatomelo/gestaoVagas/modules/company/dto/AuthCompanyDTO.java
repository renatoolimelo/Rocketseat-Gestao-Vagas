package br.com.renatomelo.gestaoVagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {
	
	private String password;
	private String username;

}
