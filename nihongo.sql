USE nihongo;
CREATE TABLE IF NOT EXISTS lecciones(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
	pregunta VARCHAR(30) NOT NULL,
    respuesta VARCHAR(10) NOT NULL,
    opcion1 VARCHAR(10) NOT NULL,
    opcion2 VARCHAR(10) NOT NULL,
    nivel INTEGER(10),
    fecha DATETIME
);
    
CREATE TABLE IF NOT EXISTS vocabulario(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
	español VARCHAR(50) NOT NULL,
	japones VARCHAR(50) NOT NULL,
    JLPT SET('N5','N4','N3','N2','N1'));
    
CREATE TABLE IF NOT EXISTS gramatica(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
	español VARCHAR(100) NOT NULL,
	japones VARCHAR(100) NOT NULL,
    JLPT SET('N5','N4','N3','N2','N1'));
    
CREATE TABLE IF NOT EXISTS kanji(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
    japones VARCHAR(10) NOT NULL,
    kunyomi VARCHAR(10),
    onyomi VARCHAR(10),
    JLPT SET('N5','N4','N3','N2','N1'),
    KANKEN ENUM('10','9','8','7','6','5','4','3','P2','2','P1','1')
);

CREATE TABLE IF NOT EXISTS usuario(
	id INTEGER(20) NOT NULL PRIMARY KEY,
    usuario VARCHAR(20) NOT NULL,
	mail VARCHAR(100) NOT NULL,
	psswd VARCHAR(20) NOT NULL);
    
CREATE TABLE IF NOT EXISTS test(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
	pregunta VARCHAR(30) NOT NULL,
    respuesta VARCHAR(10) NOT NULL,
    opcion1 VARCHAR(10) NOT NULL,
    opcion2 VARCHAR(10) NOT NULL,
    nivel INTEGER(10), #nivel adquirido por el usuario
    fecha DATE
);
