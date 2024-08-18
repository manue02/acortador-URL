package com.Shortening.acortador_URL.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Shortening.acortador_URL.models.usuarioModels;
import com.Shortening.acortador_URL.repositories.IusuarioRepository;

@Service
public class usuarioService {

    private final IusuarioRepository usuarioRepository;


    public usuarioService(IusuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<usuarioModels> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public usuarioModels saveUsuario(usuarioModels usuario) {
       return usuarioRepository.save(usuario);
    }

    public Optional<usuarioModels> findById(Integer id) {
        return usuarioRepository.findById(id);
    }
    
}
