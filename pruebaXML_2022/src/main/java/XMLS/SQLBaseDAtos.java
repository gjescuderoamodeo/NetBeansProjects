/*CREATE DATABASE IF NOT EXISTS mi_base_de_datos;
USE mi_base_de_datos;

CREATE TABLE alimento (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    proteinas DOUBLE NOT NULL,
    grasas DOUBLE NOT NULL,
    hidratos DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ingrediente (
    id INT(11) NOT NULL AUTO_INCREMENT,
    alimento_id INT(11) NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (alimento_id) REFERENCES alimento(id)
);

CREATE TABLE receta (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE receta_ingrediente (
    receta_id INT(11) NOT NULL,
    ingrediente_id INT(11) NOT NULL,
    PRIMARY KEY (receta_id, ingrediente_id),
    FOREIGN KEY (receta_id) REFERENCES receta(id),
    FOREIGN KEY (ingrediente_id) REFERENCES ingrediente(id) ON DELETE CASCADE
);*/
