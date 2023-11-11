package com.terezav.myfinances.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terezav.myfinances.data.dto.ReceitaDTO;
import com.terezav.myfinances.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/receita")
public class ReceitaController {
	
	@Autowired
	private ReceitaService service;

	@Operation(summary = "Cadastrar uma nova receita | role: [ADMIN]", tags = "receita")
	@PostMapping
	public ReceitaDTO create(@RequestBody ReceitaDTO receita) {
		return service.save(receita);
	}
	
	@Operation(summary = "Atualizar detalhes de uma receita existente  | role: [ADMIN]", tags = "receita")
	@PutMapping
	public ReceitaDTO update(@RequestBody ReceitaDTO receita) {
		return service.save(receita);
	}
	
	@Operation(summary = "Retorna os dados de uma receita a partir do ID | role: [ADMIN]", tags = "receita")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200", description = "receita retornado com sucesso"),
		@ApiResponse(responseCode = "404", description = "receita não encontrada"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor - myfinances.service.receita.notfound.exception") })
	@GetMapping("/{id}")
	public ReceitaDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@Operation(summary = "Deletar uma receita | role: [ADMIN]", tags = "receita")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@Operation(summary = "Lista dados de todas as receitas | role: [ADMIN]", tags = "receita")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
}