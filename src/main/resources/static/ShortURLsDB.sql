SET sql_mode = 'STRICT_ALL_TABLES';

DROP DATABASE IF EXISTS ShortURLsDB;
CREATE DATABASE IF NOT EXISTS ShortURLsDB
CHARACTER SET utf8mb4
  COLLATE utf8mb4_spanish_ci;

USE ShortURLsDB;

-- 1. Tabla usuarios
CREATE TABLE usuarios (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    contraseña VARCHAR(255),
    email VARCHAR(255),
    created_at DATETIME NOT NULL,
    last_login DATETIME NOT NULL,
    activo BOOLEAN NOT NULL
);

-- 2. Tabla url
CREATE TABLE url (
	  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    url_short VARCHAR(255) NOT NULL,
    url_long VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    expires_at DATETIME NOT NULL,
    last_visited DATETIME,
    user_id INT NOT NULL,
    activo BOOLEAN NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuarios(id)
);