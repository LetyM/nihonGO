USE nihongo;
    
CREATE TABLE IF NOT EXISTS palabra(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
    clave VARCHAR (30) NOT NULL,
	español VARCHAR(30) NOT NULL,
	japones VARCHAR(30) NOT NULL,
    JLPT SET('N5','N4','N3','N2','N1')
);
    
CREATE TABLE IF NOT EXISTS kanji(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
    español VARCHAR(20),
    japones VARCHAR(10) NOT NULL,
    kunyomi VARCHAR(10),
    onyomi VARCHAR(10),
    JLPT SET('N5','N4','N3','N2','N1'),
    KANKEN ENUM('10','9','8','7','6','5','4','3','P2','2','P1','1')
);
    
CREATE TABLE IF NOT EXISTS testd(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
	enunciado VARCHAR(30) NOT NULL,
    respuesta VARCHAR(10) NOT NULL,
    opcion1 VARCHAR(10) NOT NULL,
    opcion2 VARCHAR(10) NOT NULL,
    nivel INTEGER(10), #nivel adquirido por el usuario
    fecha DATE
);

CREATE TABLE IF NOT EXISTS palabkanji( #relacion entre palabra y kanji
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
    palabra VARCHAR(50) NOT NULL,
    kanji VARCHAR(10) NOT NULL
);
		
CREATE TABLE IF NOT EXISTS enunciado(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
    enunciado VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS testP(
	codigo INTEGER(20) NOT NULL PRIMARY KEY,
    nombre VARCHAR(10) NOT NULL,
    tipo INTEGER(2) NOT NULL,
    enunciado VARCHAR(30) NOT NULL,
    respuesta VARCHAR(10) NOT NULL
);
