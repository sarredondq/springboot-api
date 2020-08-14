package com.practica.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practica.model.Foto;

public interface IFoto extends MongoRepository<Foto, String> {
	
	public Foto findByIdFoto(String idFoto);
	public void deleteByIdFoto(String idFoto);
}
