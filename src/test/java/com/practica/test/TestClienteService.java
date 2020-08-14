package com.practica.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.practica.model.Cliente;
import com.practica.model.DatosCliente;
import com.practica.model.Foto;
import com.practica.repository.IDatosCliente;
import com.practica.repository.IFoto;
import com.practica.service.ClienteService;

@ExtendWith(MockitoExtension.class)
public class TestClienteService {

	@InjectMocks
	ClienteService clienteService;

	@Mock
	IDatosCliente iDatosCliente;

	@Mock
	IFoto iFoto;

	@Test
	public void testObtenerTodos() {

		// given
		Cliente cliente1 = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "foto");
		Cliente cliente2 = new Cliente(2, "laura", "jimenez", "ti", "123456", 20, "medellin", "foto");
		DatosCliente datosCliente1 = new DatosCliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin");
		DatosCliente datosCliente2 = new DatosCliente(2, "laura", "jimenez", "ti", "123456", 20, "medellin");
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<DatosCliente> datosclientes = new ArrayList<DatosCliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		datosclientes.add(datosCliente1);
		datosclientes.add(datosCliente2);

		when(iDatosCliente.findAll()).thenReturn(datosclientes);
		when(iFoto.findByIdFoto(anyString())).thenReturn(new Foto("1", "foto"));

		// when
		List<Cliente> result = clienteService.obtenerTodos();
		// then
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(clientes.get(0).getIdCliente(), result.get(0).getIdCliente());
		assertEquals(clientes.get(1).getIdCliente(), result.get(1).getIdCliente());
	}

	@Test
	public void testObtenerPorId() {

		// given
		Cliente cliente1 = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "foto");
		DatosCliente datosCliente1 = new DatosCliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin");

		when(iDatosCliente.findByIdCliente(1)).thenReturn(datosCliente1);
		when(iFoto.findByIdFoto(anyString())).thenReturn(new Foto("1", "foto"));

		// when
		Cliente result = clienteService.obtenerPorId(1);
		// then
		assertNotNull(result);
		assertEquals(cliente1.getIdCliente(), result.getIdCliente());
	}

	@Test
	public void TestCrearCliente() {

		// given
		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "foto");

		// when
		clienteService.crearCliente(cliente);

	}

	@Test
	public void TestModificarCliente() {

		// given

		DatosCliente datosCliente1 = new DatosCliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin");
		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "foto");

		when(iDatosCliente.findByIdCliente(1)).thenReturn(datosCliente1);

		// when
		clienteService.modificarCliente(1, cliente);

	}

	@Test
	public void TestEliminarCliente() {

		// given

		DatosCliente datosCliente1 = new DatosCliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin");

		when(iDatosCliente.findByIdCliente(1)).thenReturn(datosCliente1);

		// when
		clienteService.eliminarCliente(1);

	}

	@Test
	public void testObtenerMayoresDe() {

		// given
		Cliente cliente1 = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "foto");
		Cliente cliente2 = new Cliente(2, "laura", "jimenez", "ti", "123456", 20, "medellin", "foto");
		DatosCliente datosCliente1 = new DatosCliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin");
		DatosCliente datosCliente2 = new DatosCliente(2, "laura", "jimenez", "ti", "123456", 20, "medellin");
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<DatosCliente> datosclientes = new ArrayList<DatosCliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		datosclientes.add(datosCliente1);
		datosclientes.add(datosCliente2);

		when(iDatosCliente.getMayorDe(20)).thenReturn(datosclientes);
		when(iFoto.findByIdFoto(anyString())).thenReturn(new Foto("1", "foto"));

		// when
		List<Cliente> result = clienteService.obtenerMayoresDe(20);
		// then
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(clientes.get(0).getIdCliente(), result.get(0).getIdCliente());
		assertEquals(clientes.get(1).getIdCliente(), result.get(1).getIdCliente());
	}
}
