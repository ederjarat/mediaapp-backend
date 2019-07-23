package com.mitocode.controller;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Rol;
import com.mitocode.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping("")
    public ResponseEntity<List<Rol>> getAll(){
        return new ResponseEntity<>(rolService.listar(), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Rol>> listarPageable(Pageable pageable){
        return new ResponseEntity<>(rolService.listarPageable(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> listarPorId(@PathVariable("id") Integer idRol) {
        Rol rol = rolService.leer(idRol);
        if(rol == null)
            throw new ModeloNotFoundException("NO SE ENCONTRO EL ROL CON ID: "+ idRol);

        return new ResponseEntity<>(rol,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Rol rol) {
        Rol nuevoRol = rolService.registrar(rol);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rol.getIdRol()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Rol> modificar(@RequestBody Rol rol) {
        Rol oldRol = rolService.leer(rol.getIdRol());
        if(oldRol == null)
            throw new ModeloNotFoundException("NO SE ENCONTRO EL ROL CON ID: "+ rol.getIdRol());
        rolService.modificar(rol);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer idRol) {
        Rol rol = rolService.leer(idRol);
        if(rol == null)
            throw new ModeloNotFoundException("NO SE ENCONTRO EL ROL CON ID: "+ idRol);

        rolService.eliminar(idRol);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
