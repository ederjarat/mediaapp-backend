package com.mitocode.service.impl;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;
import com.mitocode.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepo usuarioRepo;

    @Override
    public Usuario registrar(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario modificar(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario leer(Integer id) {
        return usuarioRepo.findOne(id);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Override
    public void eliminar(Integer id) {

    }
}
