package com.terezav.myfinances.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLowDTO {
	
	@JsonProperty("nome")
	private String name;
	
	private String email;
	private String login;

}
