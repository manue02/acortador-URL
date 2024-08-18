package com.Shortening.acortador_URL.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Shortening.acortador_URL.models.usuarioModels;

@Repository
public interface IusuarioRepository extends JpaRepository<usuarioModels, Integer> {
    
}
