package com.coding.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.modelo.Canciones;
import com.coding.repositorio.RepositorioCanciones;

@Service
public class ServicioCanciones {
	
	@Autowired
	private final RepositorioCanciones repositorioCanciones;

	public ServicioCanciones(RepositorioCanciones repositorioCanciones) {
		super();
		this.repositorioCanciones = repositorioCanciones;
	}
	
	public Canciones agregarCancion(Canciones nuevaCancion) {
		return this.repositorioCanciones.save(nuevaCancion);
	}
	
	public List<Canciones> obtenerTodasCanciones(){
		return this.repositorioCanciones.findAll();
	}
	
	public Canciones obtenerPorId(Long id) {
		return this.repositorioCanciones.findById(id).orElse(null);
	}
	
	public void eliminarPorId(Long id) {
		this.repositorioCanciones.deleteById(id);
	}
	
	public Canciones actualizarCancion(Canciones cancion) {
		return this.repositorioCanciones.save(cancion);
	}
	
	
}
