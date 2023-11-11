package com.terezav.myfinances.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaDTO {
	
	private Long id;
	private String name;
	private String origin;
	private String value;
	private String data;

}
