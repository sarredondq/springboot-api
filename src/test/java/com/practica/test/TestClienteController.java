package com.practica.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;

import com.practica.controller.ClienteController;
import com.practica.model.Cliente;
import com.practica.service.ClienteService;

@ExtendWith(MockitoExtension.class)
public class TestClienteController {

	@InjectMocks
	ClienteController clienteController;

	@Mock
	ClienteService clienteService;

	@Test
	public void testListar() {
		// given
		Cliente cliente1 = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");
		Cliente cliente2 = new Cliente(2, "laura", "jimenez", "ti", "123456", 14, "medellin", "fotoLaura");
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		when(clienteService.obtenerTodos()).thenReturn(clientes);

		// when
		ResponseEntity<?> result = clienteController.listar();
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(200);
		assertNotNull(result.getBody());
		assertEquals(clientes, result.getBody());

	}

	@Test
	public void testListarCatchException() {
		// given
		when(clienteService.obtenerTodos()).thenThrow(new NoSuchElementException());

		// when
		ResponseEntity<?> result = clienteController.listar();
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testObtener() {
		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");
		when(clienteService.obtenerPorId(1)).thenReturn(cliente);

		// when
		ResponseEntity<?> result = clienteController.obtener(1);

		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(200);
		assertNotNull(result.getBody());
		assertEquals(cliente, result.getBody());
	}

	@Test
	public void testObtenerCatchNullPointerException() {
		// given
		when(clienteService.obtenerPorId(1)).thenThrow(new NullPointerException());

		// when
		ResponseEntity<?> result = clienteController.obtener(1);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testObtenerCatchException() {
		// given
		when(clienteService.obtenerPorId(1)).thenThrow(new NoSuchElementException());

		// when
		ResponseEntity<?> result = clienteController.obtener(1);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testInsertar() {
		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");
		// when
		ResponseEntity<String> result = clienteController.insertar(cliente);

		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(200);
		assertNotNull(result.getBody());
		assertEquals("Cliente creado satisfactoriamete!", result.getBody());

	}

	@Test
	public void testInsertarCatchTransactionSystemException() {
		// given
		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");

		doThrow(new TransactionSystemException("")).when(clienteService).crearCliente(cliente);

		// when
		ResponseEntity<?> result = clienteController.insertar(cliente);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testInsertarCatchException() {
		// given
		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");

		doThrow(new NoSuchElementException()).when(clienteService).crearCliente(cliente);

		// when
		ResponseEntity<?> result = clienteController.insertar(cliente);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testModificar() {
		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");

		// when
		ResponseEntity<?> result = clienteController.modificar(1, cliente);

		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(200);
		assertNotNull(result.getBody());
		assertEquals("Cliente modificado satisfactoriamente!", result.getBody());
	}

	@Test
	public void testModificarCatchTransactionSystemException() {

		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");

		// given
		doThrow(new TransactionSystemException("")).when(clienteService).modificarCliente(1, cliente);

		// when
		ResponseEntity<?> result = clienteController.modificar(1, cliente);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testModificarCatchNullPointerException() {

		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");

		// given
		doThrow(new NullPointerException()).when(clienteService).modificarCliente(1, cliente);

		// when
		ResponseEntity<?> result = clienteController.modificar(1, cliente);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testModificarCatchException() {

		Cliente cliente = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");

		// given
		doThrow(new NoSuchElementException()).when(clienteService).modificarCliente(1, cliente);

		// when
		ResponseEntity<?> result = clienteController.modificar(1, cliente);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testEliminar() {
		// when
		ResponseEntity<?> result = clienteController.eliminar(1);

		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(200);
		assertNotNull(result.getBody());
		assertEquals("Cliente eliminado satisfactoriamente!", result.getBody());
	}

	@Test
	public void testEliminarCatchException() {

		// given
		doThrow(new NoSuchElementException()).when(clienteService).eliminarCliente(1);

		// when
		ResponseEntity<?> result = clienteController.eliminar(1);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}
	
	@Test
	public void testEliminarCatchNullPointerException() {

		// given
		doThrow(new NullPointerException()).when(clienteService).eliminarCliente(1);

		// when
		ResponseEntity<?> result = clienteController.eliminar(1);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

	@Test
	public void testMayoresDe() {
		// given
		Cliente cliente1 = new Cliente(1, "santiago", "arredondo", "cc", "12345", 21, "medellin", "fotoSantiago");
		Cliente cliente2 = new Cliente(2, "laura", "jimenez", "ti", "123456", 22, "medellin", "fotoLaura");
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		when(clienteService.obtenerMayoresDe(20)).thenReturn(clientes);

		// when
		ResponseEntity<?> result = clienteController.MayoresDe(20);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(200);
		assertNotNull(result.getBody());
		assertEquals(clientes, result.getBody());
	}
	
	@Test
	public void testMayoresDeCatchException() {
		// given
		when(clienteService.obtenerMayoresDe(20)).thenThrow(new NoSuchElementException());

		// when
		ResponseEntity<?> result = clienteController.MayoresDe(20);
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(400);
		assertNotNull(result.getBody());

	}

}
