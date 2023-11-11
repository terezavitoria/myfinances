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

import com.terezav.myfinances.data.dto.DespesaDTO;
import com.terezav.myfinances.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/despesa")
public class DespesaController {
	
	@Autowired
	private DespesaService service;

	@Operation(summary = "Cadastra uma nova despesa | role: [ADMIN]", tags = "despesa")
	@PostMapping
	public DespesaDTO create(@RequestBody DespesaDTO despesa) {
		return service.save(despesa);
	}
	
	@Operation(summary = "Atualizar detalhes de uma despesa existente  | role: [ADMIN]", tags = "despesa")
	@PutMapping
	public DespesaDTO update(@RequestBody DespesaDTO despesa) {
		return service.save(despesa);
	}
	
	@Operation(summary = "Retorna os dados de uma despesa a partir do ID | role: [ADMIN]", tags = "despesa")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200", description = "Despesa retornado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Despesa não encontrada"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor - myfinances.service.despesa.notfound.exception") })
	@GetMapping("/{id}")
	public DespesaDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@Operation(summary = "Deletar uma despesa | role: [ADMIN]", tags = "despesa")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@Operation(summary = "Lista dados de todas as despesas | role: [ADMIN]", tags = "despesa")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
}
	