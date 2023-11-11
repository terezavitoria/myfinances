package com.terezav.myfinances.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespesaDTO {
	
	private Long id;
	private String name;
	private String category;
	private String value;
	private String date;

}
