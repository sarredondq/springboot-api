package com.practica.model;

// Composición de DatosCLiente y Foto

public class Cliente {

	private int idCliente;

	private String nombre;

	private String apellido;

	private String tipoDeIdentificacion;

	private String numeroDeIdentificacion;

	private int edad;

	private String ciudadDeNacimiento;

	private String foto;

	public Cliente(int idCliente, String nombre, String apellido, String tipoDeIdentificacion,
			String numeroDeIdentificacion, int edad, String ciudadDeNacimiento, String foto) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDeIdentificacion = tipoDeIdentificacion;
		this.numeroDeIdentificacion = numeroDeIdentificacion;
		this.edad = edad;
		this.ciudadDeNacimiento = ciudadDeNacimiento;
		this.foto = foto;
	}

	public Cliente() {

	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoDeIdentificacion() {
		return tipoDeIdentificacion;
	}

	public void setTipoDeIdentificacion(String tipoDeIdentificacion) {
		this.tipoDeIdentificacion = tipoDeIdentificacion;
	}

	public String getNumeroDeIdentificacion() {
		return numeroDeIdentificacion;
	}

	public void setNumeroDeIdentificacion(String numeroDeIdentificacion) {
		this.numeroDeIdentificacion = numeroDeIdentificacion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCiudadDeNacimiento() {
		return ciudadDeNacimiento;
	}

	public void setCiudadDeNacimiento(String ciudadDeNacimiento) {
		this.ciudadDeNacimiento = ciudadDeNacimiento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void llenar(DatosCliente datosCliente, Foto foto) {
		this.idCliente = datosCliente.getIdCliente();
		this.nombre = datosCliente.getNombre();
		this.apellido = datosCliente.getApellido();
		this.tipoDeIdentificacion = datosCliente.getTipoDeIdentificacion();
		this.numeroDeIdentificacion = datosCliente.getNumeroDeIdentificacion();
		this.edad = datosCliente.getEdad();
		this.ciudadDeNacimiento = datosCliente.getCiudadDeNacimiento();
		this.foto = foto.getFotoBase64();
	}

}
