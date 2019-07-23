package com.mitocode.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mitocode.model.Menu;
import com.mitocode.service.IMenuService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/menus")
public class MenuController {
	
	@Autowired
	private IMenuService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Menu>> listar() {
		List<Menu> menues = new ArrayList<>();
		menues = service.listar();
		return new ResponseEntity<List<Menu>>(menues, HttpStatus.OK);
	}
	
	@PostMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Menu>> listar(@RequestBody String nombre) {
		List<Menu> menues = new ArrayList<>();
		menues = service.listarMenuPorUsuario(nombre);
		return new ResponseEntity<List<Menu>>(menues, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Menu menu) {
		Menu nuevoMenu = service.registrar(menu);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(menu.getIdMenu()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Rol> modificar(@RequestBody Menu menu) {
		Menu oldMenu = service.leer(menu.getIdMenu());
		if(oldMenu == null)
			throw new ModeloNotFoundException("NO SE ENCONTRO EL MENU CON ID: "+ menu.getIdMenu());
		service.modificar(menu);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer idMenu) {
		Menu menu = service.leer(idMenu);
		if(menu == null)
			throw new ModeloNotFoundException("NO SE ENCONTRO EL MENU CON ID: "+ idMenu);

		service.eliminar(idMenu);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
