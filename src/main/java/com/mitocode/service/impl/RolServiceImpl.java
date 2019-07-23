package com.mitocode.service.impl;

import com.mitocode.model.Rol;
import com.mitocode.repo.IRolRepo;
import com.mitocode.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepo rolRepo;

    @Override
    public Rol registrar(Rol rol) {
        return rolRepo.save(rol);
    }

    @Override
    public Rol modificar(Rol rol) {
        return rolRepo.save(rol);
    }

    @Override
    public Rol leer(Integer id) {
        return rolRepo.findOne(id);
    }

    @Override
    public List<Rol> listar() {
        return rolRepo.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        rolRepo.delete(id);
    }

    @Override
    public Page<Rol> listarPageable(Pageable pageable) {
        return rolRepo.findAll(pageable);
    }
}
