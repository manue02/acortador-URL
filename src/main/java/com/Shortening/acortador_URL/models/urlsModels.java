package com.Shortening.acortador_URL.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "url")
public class urlsModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_short")
    private String shortUrl;

    @Column(name = "url_long")
    private String longUrl;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "expires_at")
    private LocalDate expiresAt;

    @Column(name = "last_visited")
    private LocalDate lastVisited;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private usuarioModels usuario;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDate expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDate getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(LocalDate lastVisited) {
        this.lastVisited = lastVisited;
    }

    public usuarioModels getUsuario() {
        return usuario;
    }

    public void setUsuario(usuarioModels usuario) {
        this.usuario = usuario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public urlsModels() {
    }

    public urlsModels(String shortUrl, String longUrl, LocalDate createdAt, LocalDate expiresAt, LocalDate lastVisited,
            Boolean activo, usuarioModels usuario) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.lastVisited = lastVisited;
        this.activo = activo;
        this.usuario = usuario;
    }

   
}