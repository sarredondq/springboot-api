package com.practica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.model.Cliente;
import com.practica.service.ClienteService;

@CrossOrigin
@RestController
@Validated
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			return ResponseEntity.ok().body(clienteService.obtenerTodos());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage());
		}
	}

	@GetMapping(value = "/obtener/{id}")
	public ResponseEntity<?> obtener(@PathVariable(value = "id") Integer id) {
		try {
			return ResponseEntity.ok().body(clienteService.obtenerPorId(id));
		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage()
					+ "\nNo se encontro ningun cliente con id: " + id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage());
		}
	}

	@PostMapping("/insertar")
	public ResponseEntity<String> insertar(@Valid @RequestBody Cliente cliente) {
		try {
			clienteService.crearCliente(cliente);
			return ResponseEntity.ok().body("Cliente creado satisfactoriamete!");
		} catch (TransactionSystemException e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage()
					+ "\nPosible error en los argumentos pasados por el JSON");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage());
		}

	}

	@PutMapping(value = "/modificar/{id}")
	public ResponseEntity<String> modificar(@PathVariable(value = "id") Integer id, @Valid @RequestBody Cliente cliente) {
		try {
			clienteService.modificarCliente(id, cliente);
			return ResponseEntity.ok().body("Cliente modificado satisfactoriamente!");
		} catch (TransactionSystemException e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage()
					+ "\nPosible error en los argumentos pasados por el JSON");
		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage()
					+ "\nNo se encontro ningun cliente con id: " + id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage());
		}
	}

	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable(value = "id") Integer id) {
		try {
			clienteService.eliminarCliente(id);
			return ResponseEntity.ok().body("Cliente eliminado satisfactoriamente!");
		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage()
					+ "\nNo se encontro ningun cliente con id: " + id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage());
		}
	}

	@GetMapping(value = "/obtener/mayoresDe/{edad}")
	public ResponseEntity<?> MayoresDe(@PathVariable(value = "edad") Integer edad) {
		try {
			return ResponseEntity.ok().body(clienteService.obtenerMayoresDe(edad));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getClass() + "\nMensaje: " + e.getMessage());
		}
	}

}
