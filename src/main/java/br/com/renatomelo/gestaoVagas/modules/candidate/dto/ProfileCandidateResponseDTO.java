package br.com.renatomelo.gestaoVagas.modules.candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
	
	private String description;
	private String usernme;
	private String email;
	private UUID id;
	private String name;

}
