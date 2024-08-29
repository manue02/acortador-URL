package com.Shortening.acortador_URL.controllers;

import java.net.URI;
import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

import com.Shortening.acortador_URL.models.urlsModels;
import com.Shortening.acortador_URL.services.urlsService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class urlsController {

    private final urlsService urlsService;

    public urlsController(urlsService urlsService) {
        this.urlsService = urlsService;
    }

    @Operation(summary = "Convertir una nueva URL", description = "Convierte una URL larga en una URL corta")
    @PostMapping("create-short")
    public String convertToShortUrl(@RequestBody urlsModels request) {
        
        return urlsService.convertToShortUrl(request);
    }

    @CrossOrigin(origins = "http://localhost:8100") // Reemplaza con la URL de tu Swagger UI
    @Operation(summary = "Redireccionar", description = "Redirecciona a la URL original a partir de la URL corta")
    @GetMapping("redirect/{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        
        var originalUrl = urlsService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }
    
}
