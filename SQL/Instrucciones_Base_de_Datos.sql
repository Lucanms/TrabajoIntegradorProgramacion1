CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;


-- Tabla: libro (A)
CREATE TABLE libro (
    id_libro BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(120) NOT NULL,
    editorial VARCHAR(100),
    anio_edicion INT,
    
    UNIQUE KEY uk_libro_titulo_autor (titulo, autor)
);

-- Tabla: ficha_bibliografica (B)
CREATE TABLE ficha_bibliografica (
    id_ficha_bibliografica BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    isbn VARCHAR(17) UNIQUE,
    clasificacion_dewey VARCHAR(20),
    estanteria VARCHAR(20),
    idioma VARCHAR(30),

    id_libro BIGINT NOT NULL,
    
    UNIQUE KEY uk_libro_1to1 (id_libro),

    CONSTRAINT fk_ficha_libro
        FOREIGN KEY (id_libro)
        REFERENCES libro(id_libro)
        ON DELETE CASCADE
);

