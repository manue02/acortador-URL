package com.Shortening.acortador_URL.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Shortening.acortador_URL.models.urlsModels;
import com.Shortening.acortador_URL.models.usuarioModels;
import com.Shortening.acortador_URL.repositories.IurlsRepository;
import com.Shortening.acortador_URL.repositories.IusuarioRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class urlsService {

    private final IurlsRepository urlsRepository;
    private final baseService baseService;
    private final IusuarioRepository usuarioRepository;

    public urlsService(IurlsRepository urlsRepository, baseService baseService, IusuarioRepository usuarioRepository) {
        this.urlsRepository = urlsRepository;
        this.baseService = baseService;
        this.usuarioRepository = usuarioRepository;
    }

    public String convertToShortUrl(urlsModels request) {
        var url = new urlsModels();

        Optional<usuarioModels> usuarioEncontrado = findById(request.getUsuario().getId());

        if (usuarioEncontrado.isPresent()) { 

            url.setLongUrl(request.getLongUrl());
            url.setCreatedAt(LocalDate.now());
            url.setActivo(true);
            url.setExpiresAt(request.getExpiresAt());
            url.setLastVisited(request.getLastVisited());
            url.setUsuario(usuarioEncontrado.get());
            url.setShortUrl(baseService.encode());

            var entity =  urlsRepository.save(url);

            return entity.getShortUrl();
        } else {
            throw new EntityNotFoundException("User not found");
        }
        
    }

    public String getOriginalUrl(String shortUrl) {

        var id = baseService.decode(shortUrl);
        var entity = urlsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (entity.getExpiresAt() != null && entity.getExpiresAt().isBefore(LocalDate.now())) {
           
                entity.setActivo(false);
                urlsRepository.save(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getLongUrl();
    }

    public urlsModels saveUrl(urlsModels url) {
        return urlsRepository.save(url);
    }

    public Optional<usuarioModels> findById(Integer id) {
        return usuarioRepository.findById(id);
    }
    
}
