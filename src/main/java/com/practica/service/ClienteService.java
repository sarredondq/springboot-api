package com.practica.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.practica.model.Cliente;
import com.practica.model.DatosCliente;
import com.practica.model.Foto;
import com.practica.repository.IDatosCliente;
import com.practica.repository.IFoto;

@Service
public class ClienteService {

	@Autowired
	private IDatosCliente iDatosCliente;

	@Autowired
	private IFoto iFoto;

	public List<Cliente> obtenerTodos() {
		List<Cliente> lCliente = new ArrayList<Cliente>();
		List<DatosCliente> lDatosClinetes = iDatosCliente.findAll();
		for (DatosCliente datosCliente : lDatosClinetes) {
			Foto foto = iFoto.findByIdFoto(datosCliente.getNumeroDeIdentificacion());
			Cliente cliente = new Cliente();
			cliente.llenar(datosCliente, foto);
			lCliente.add(cliente);
		}
		return lCliente;
	}

	public Cliente obtenerPorId(Integer id) throws NullPointerException {
		DatosCliente datosCliente = iDatosCliente.findByIdCliente(id);
		Foto foto = iFoto.findByIdFoto(datosCliente.getNumeroDeIdentificacion());
		Cliente cliente = new Cliente();
		cliente.llenar(datosCliente, foto);
		return cliente;
	}

	public void crearCliente(Cliente cliente) throws TransactionSystemException {
		DatosCliente datosCliente = new DatosCliente();
		datosCliente.llenar(cliente);
		Foto foto = new Foto();
		foto.llenar(cliente);
		iDatosCliente.save(datosCliente);
		iFoto.save(foto);
	}

	public void modificarCliente(Integer id, Cliente cliente) throws TransactionSystemException, NullPointerException {
		DatosCliente datosClienteSave = iDatosCliente.findByIdCliente(id);
		String idFoto = datosClienteSave.getNumeroDeIdentificacion();		
		datosClienteSave.actualizar(cliente);		
		iDatosCliente.save(datosClienteSave);		
		
		// se borra la foto por que no modificaba y se creaba otro (a corregir)
		Foto foto = new Foto();
		foto.llenar(cliente);
		iFoto.deleteByIdFoto(idFoto);
		iFoto.save(foto);
	}

	public void eliminarCliente(Integer id) throws NullPointerException {
		DatosCliente datosCliente = iDatosCliente.findByIdCliente(id);
		iFoto.deleteByIdFoto(datosCliente.getNumeroDeIdentificacion());
		iDatosCliente.deleteById(id);

	}

	public List<Cliente> obtenerMayoresDe(Integer edad) {
		List<Cliente> lCliente = new ArrayList<Cliente>();
		List<DatosCliente> lDatosClinetes = iDatosCliente.getMayorDe(edad);
		for (DatosCliente datosCliente : lDatosClinetes) {
			Foto foto = iFoto.findByIdFoto(datosCliente.getNumeroDeIdentificacion());
			Cliente cliente = new Cliente();
			cliente.llenar(datosCliente, foto);
			lCliente.add(cliente);
		}
		return lCliente;
	}

}
