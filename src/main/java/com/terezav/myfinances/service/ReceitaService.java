package com.terezav.myfinances.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.terezav.myfinances.Converter.DozerConverter;
import com.terezav.myfinances.data.dto.DespesaDTO;
import com.terezav.myfinances.data.dto.ReceitaDTO;
import com.terezav.myfinances.data.model.Receita;
import com.terezav.myfinances.exception.CommonsException;
import com.terezav.myfinances.repository.ReceitaRepository;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	
	public ReceitaDTO save(ReceitaDTO receita) {
		if(receita.getName().length() > 100) {
			throw new CommonsException(HttpStatus.BAD_REQUEST, 
					"terezav.myfinances.service.receita.badrequest.exception", 
					"O nome do usuário excede o limite de 100 caracteres.");
		}
		
		
		if(receita.getId() != null) {
			this.findById(receita.getId());
		}
		
		var receitaAux = DozerConverter.parseObject(receita, Receita.class);
		var receitaDTOsaved = DozerConverter
				.parseObject(repository.save(receitaAux),ReceitaDTO.class);
		
		return (ReceitaDTO) receitaDTOsaved;
	}
	
	public void delete(Long id) {
		this.findById(id);
		repository.deleteById(id);
	}
	
	
	public ReceitaDTO findById(Long id) {
		var despesa = repository.findById(id);
		if(despesa == null || despesa.isEmpty()) {
			throw new CommonsException(HttpStatus.NOT_FOUND, 
					"terezav.myfinances.service.despesa.notfound.exception",
					"Usuário não encontrado.");
		}
		return DozerConverter.parseObject(despesa.get(), ReceitaDTO.class);
	}
	
	public List<DespesaDTO> findAll(){
		return DozerConverter.parseListObjects
				(repository.findAll(), DespesaDTO.class);
	}
	
	
}
