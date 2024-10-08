package com.Shortening.acortador_URL.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Shortening.acortador_URL.models.urlsModels;

@Repository
public interface IurlsRepository extends JpaRepository<urlsModels, Long> {

    Optional<urlsModels> findByShortUrl(String shortUrl);    
}