package com.Shortening.acortador_URL.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shortening.acortador_URL.services.usuarioService;
import com.Shortening.acortador_URL.models.usuarioModels;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuario")
public class usuarioController {

    private final usuarioService usuarioService;

    public usuarioController(usuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene todos los usuarios registrados")
    @GetMapping("/todos")
    public ResponseEntity<?> getTodosUsuarios() {
        try {
            List<usuarioModels> tiposExpedientes = usuarioService.getAllUsuarios();
            return ResponseEntity.ok(tiposExpedientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener todos los tipos de expedientes: " + e.getMessage());
        }
    }

    @Operation(summary = "Insertar nuevo usuario", description = "Inserta un nuevo usuario")
    @GetMapping("/insertar/{nombre}/{apellido}/{email}/{password}")
    public ResponseEntity<?> insertarUsuario(@PathVariable String nombre, @PathVariable String apellido, @PathVariable String email, @PathVariable String password) {
        try {
            var usuario = new usuarioModels();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setActivo(true);
            usuario.setCreatedAt(LocalDateTime.now());
            usuario.setLastLogin(LocalDateTime.now());
            return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al insertar el usuario: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza un usuario")
    @GetMapping("/actualizar/{id}/{nombre}/{apellido}/{email}/{password}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @PathVariable String nombre, @PathVariable String apellido, @PathVariable String email, @PathVariable String password) {
        try {

            Optional<usuarioModels> usuarioEncontrado = usuarioService.findById(id);

            if (usuarioEncontrado.isPresent()) { 
    
            var usuario = usuarioEncontrado.get();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setPassword(password);

            return ResponseEntity.ok( usuarioService.saveUsuario(usuario));
            }else{ 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado"); 
            }   
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario")
    @GetMapping("/eliminar/{id}/{activo}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id, @PathVariable Boolean activo) {
        try {
            Optional<usuarioModels> usuarioEncontrado = usuarioService.findById(id);

            if (usuarioEncontrado.isPresent()) { 
               
                var usuarioEliminar = usuarioEncontrado.get();
                
                if (activo) {
                    usuarioEliminar.setActivo(false);
                    return ResponseEntity.ok(usuarioService.saveUsuario(usuarioEliminar));
                } else {
                    usuarioEliminar.setActivo(true);
                    return ResponseEntity.ok(usuarioService.saveUsuario(usuarioEliminar));
                }
                
            }else{ 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado"); 
            }   
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }
    
}
