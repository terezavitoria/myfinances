package com.terezav.myfinances.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.terezav.myfinances.Converter.DozerConverter;
import com.terezav.myfinances.data.dto.UserDTO;
import com.terezav.myfinances.data.dto.UserLowDTO;
import com.terezav.myfinances.data.model.User;
import com.terezav.myfinances.exception.CommonsException;
import com.terezav.myfinances.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public UserDTO save(UserDTO dto) {
		if(dto.getName().length() > 100) {
			throw new CommonsException(HttpStatus.BAD_REQUEST, 
					"terezav.myfinances.service.user.badrequest.exception", 
					"O nome do usuário excede o limite de 100 caracteres.");
		}
		
		if(!(repository.findByEmail(dto.getEmail()) == null)) {
			throw new CommonsException(HttpStatus.CONFLICT, 
					"terezav.myfinances.service.user.conflict.exception", 
					"O email informado já existe.");
		}
		
		if(!(repository.findByLogin(dto.getLogin()) == null)) {
			throw new CommonsException(HttpStatus.CONFLICT, 
					"terezav.myfinances.service.user.conflict.exception", 
					"O login informado já existe.");
		}
		
		if(dto.getId() != null) {
			this.findById(dto.getId());
		}
		
		var user = DozerConverter.parseObject(dto, User.class);
		var userDTOSaved = DozerConverter
				.parseObject(repository.save(user), UserDTO.class);
		
		return userDTOSaved;
	}
	
	public void delete(Long id) {
		this.findById(id);
		repository.deleteById(id);
	}
	
	
	
	public UserDTO findById(Long id) {
		var user = repository.findById(id);
		if(user == null || user.isEmpty()) {
			throw new CommonsException(HttpStatus.NOT_FOUND, 
					"terezav.myfinances.service.user.notfound.exception",
					"Usuário não encontrado.");
		}
		return DozerConverter.parseObject(user.get(), UserDTO.class);
	}
	
	public List<UserLowDTO> findAll(){
		return DozerConverter.parseListObjects
				(repository.findAll(), UserLowDTO.class);
	}
	
	
}
