package com.practica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practica.model.DatosCliente;

public interface IDatosCliente extends JpaRepository<DatosCliente, Integer> {

	public DatosCliente findByIdCliente(int id);
	
	@Query(value = "SELECT * FROM cliente WHERE edad >= :edad", nativeQuery = true)
	public List<DatosCliente> getMayorDe(@Param("edad") Integer edad);
	

}
