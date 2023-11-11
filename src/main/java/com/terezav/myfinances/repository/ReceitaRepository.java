package com.terezav.myfinances.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terezav.myfinances.data.model.Receita;

public interface ReceitaRepository 
						extends JpaRepository<Receita, Long>{
	


}
