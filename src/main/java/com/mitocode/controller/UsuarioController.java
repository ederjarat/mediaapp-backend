package com.mitocode.controller;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id){
        Usuario user = usuarioService.leer(id);
        if(user == null)
            throw new ModeloNotFoundException("NO SE ENCONTRO EL USUARIO CON ID: "+ id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Usuario> asignarRoles( @RequestBody Usuario usuario){
         usuarioService.registrar(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
