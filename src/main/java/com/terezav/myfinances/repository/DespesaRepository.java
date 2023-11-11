package com.terezav.myfinances.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terezav.myfinances.data.model.Despesa;

public interface DespesaRepository 
						extends JpaRepository<Despesa, Long>{

}
