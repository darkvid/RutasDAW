CREATE TABLE usuarios(
    id INTEGER not null generated always as identity (start with 1, increment by 1),
    dni VARCHAR(20),
    nombre VARCHAR(255),
    apellidos VARCHAR(255),
    mail VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY(id)
);

    id INTEGER not null generated always as identity (start with 1, increment by 1),
CREATE TABLE roles(
    rolname VARCHAR (255),
    dniUser VARCHAR (255),
    PRIMARY KEY(id)
);

CREATE TABLE rutas (
	id INTEGER not null generated always as identity (start with 1, increment by 1),
	nombre VARCHAR (50),
	tipo VARCHAR (20),
	fecha DATE,
	origen VARCHAR (100),
	destino VARCHAR (100),
	valoracion INTEGER,
	distancia INTEGER,
	nombreFichero VARCHAR (255),
	PRIMARY KEY(id)
);

CREATE TABLE UsuarioRuta(
	id_usuario INTEGER,
	id_ruta INTEGER
);

CREATE TABLE PuntosRuta(
	id_ruta INTEGER,
	lat REAL
);

INSERT INTO (dni, nombre, apellidos, mail, password)
    VALUES ('1','admin','admin','admin@admin.com','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

INSERT INTO  roles (rolname, dniUser) values ('admin','1');
