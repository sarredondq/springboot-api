package com.practica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "cliente")
public class DatosCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCliente;
	@Column
	@Pattern(regexp = "[A-Za-z]+", message = "Nombre Invalido")
	private String nombre;
	@Column
	@Pattern(regexp = "[A-Za-z]+", message = "Apellido Invalido")
	private String apellido;
	@Column
	@Pattern(regexp = "[A-Za-z]+", message = "Tipo de identificación Invalido")
	private String tipoDeIdentificacion;
	@Column(unique = true)
	@Pattern(regexp = "[0-9]+", message = "Numero de identificación Invalido")
	private String numeroDeIdentificacion;
	@Column
	private int edad;
	@Column
	@Pattern(regexp="[A-Za-z]+",message = "Ciudad de nacimiendo Invalido")
	private String ciudadDeNacimiento;

	public DatosCliente(int idCliente, String nombre, String apellido, String tipoDeIdentificacion,
			String numeroDeIdentificacion, int edad, String ciudadDeNacimiento) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDeIdentificacion = tipoDeIdentificacion;
		this.numeroDeIdentificacion = numeroDeIdentificacion;
		this.edad = edad;
		this.ciudadDeNacimiento = ciudadDeNacimiento;
	}

	public DatosCliente() {

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

	public void llenar(Cliente cliente) {
		this.idCliente = cliente.getIdCliente();
		this.nombre = cliente.getNombre();
		this.apellido = cliente.getApellido();
		this.tipoDeIdentificacion = cliente.getTipoDeIdentificacion();
		this.numeroDeIdentificacion = cliente.getNumeroDeIdentificacion();
		this.edad = cliente.getEdad();
		this.ciudadDeNacimiento = cliente.getCiudadDeNacimiento();
	}

	public void actualizar(Cliente cliente) {
		this.nombre = cliente.getNombre();
		this.apellido = cliente.getApellido();
		this.tipoDeIdentificacion = cliente.getTipoDeIdentificacion();
		this.numeroDeIdentificacion = cliente.getNumeroDeIdentificacion();
		this.edad = cliente.getEdad();
		this.ciudadDeNacimiento = cliente.getCiudadDeNacimiento();
	}

}
