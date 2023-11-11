package com.terezav.myfinances.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.terezav.myfinances.Converter.DozerConverter;
import com.terezav.myfinances.data.dto.DespesaDTO;
import com.terezav.myfinances.data.model.Despesa;
import com.terezav.myfinances.exception.CommonsException;
import com.terezav.myfinances.repository.DespesaRepository;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository repository;
	
	public DespesaDTO save(DespesaDTO despesa) {
		if(despesa.getName().length() > 100) {
			throw new CommonsException(HttpStatus.BAD_REQUEST, 
					"terezav.myfinances.service.despesa.badrequest.exception", 
					"O nome do usuário excede o limite de 100 caracteres.");
		}
		
		
		
		if(despesa.getId() != null) {
			this.findById(despesa.getId());
		}
		
		var despesaAux = DozerConverter.parseObject(despesa, Despesa.class);
		var despesaDTOsaved = DozerConverter
				.parseObject(repository.save(despesaAux), DespesaDTO.class);
		
		return (DespesaDTO) despesaDTOsaved;
	}
	
	public void delete(Long id) {
		this.findById(id);
		repository.deleteById(id);
	}
	
	
	public DespesaDTO findById(Long id) {
		var despesa = repository.findById(id);
		if(despesa == null || despesa.isEmpty()) {
			throw new CommonsException(HttpStatus.NOT_FOUND, 
					"terezav.myfinances.service.despesa.notfound.exception",
					"Usuário não encontrado.");
		}
		return DozerConverter.parseObject(despesa.get(), DespesaDTO.class);
	}
	
	public List<DespesaDTO> findAll(){
		return DozerConverter.parseListObjects
				(repository.findAll(), DespesaDTO.class);
	}
	
	
}
