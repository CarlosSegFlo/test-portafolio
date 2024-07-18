package com.coding.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.modelo.Canciones;

@Repository
public interface RepositorioCanciones extends CrudRepository<Canciones,Long>{
	List<Canciones> findAll();

}
