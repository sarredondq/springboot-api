package com.practica.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "foto")
public class Foto {

	// Numero de identificación del Cliente
	@Id
	@Indexed(unique = true)
	private String idFoto;

	private String fotoBase64;

	public Foto(String idFoto, String fotoBase64) {
		this.idFoto = idFoto;
		this.fotoBase64 = fotoBase64;
	}

	public Foto() {

	}

	public String getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(String idFoto) {
		this.idFoto = idFoto;
	}

	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}

	public void llenar(Cliente cliente) {
		this.idFoto = cliente.getNumeroDeIdentificacion();
		this.fotoBase64 = cliente.getFoto();
	}

	public void actualizar(Cliente cliente) {
		this.idFoto = cliente.getNumeroDeIdentificacion();
		this.fotoBase64 = cliente.getFoto();
	}

}
