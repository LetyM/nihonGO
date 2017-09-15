USE nihongo;

#                       codigo, español, japones, kunyomi, onyomi, JLPT, KANKEN
INSERT INTO kanji VALUES (0,'Luna, mes','月','つき','ゲツ   ガツ','N5','10');
INSERT INTO kanji VALUES (1,'Sol, día','日','ひ','ニチ   ジツ','N5','10');
INSERT INTO kanji VALUES (2,'Uno', '一','ひと','イチ   イツ','N5','10');

#	                codigo	pregunta respuesta	opcion1	opcion2	nivel fecha
INSERT INTO testd VALUES (0,'Lectura de 月','つき','ひ','つさ',0,'2017-05-10');
INSERT INTO testd VALUES (1,'Lectura de 日','ひ','つき','ひき',1,'2017-05-10');
INSERT INTO testd VALUES (2,'Lectura de 一','いち','にち','に',0,'2017-05-10');
INSERT INTO testd VALUES (3,'Lectura de 二','に','こ','さん',0,'2017-05-10');
INSERT INTO testd VALUES (4,'Lectura de 三','さん','きく','いち',4,'2017-06-11');

# codigo   clave   español   japones   JLPT
INSERT INTO palabra VALUES (0,'Luna - つき','Luna','つき','N5');
INSERT INTO palabra VALUES (1,'Mes - がつ','Mes','がつ','N5');
INSERT INTO palabra VALUES (2,'Fuego - ひ','Fuego','ひ','N5');
INSERT INTO palabra VALUES (3,'Sol - ひ','Sol','ひ','N5');
INSERT INTO palabra VALUES (4,'Tiempo - てんき','Tiempo','てんき','N5');
INSERT INTO palabra VALUES (5,'Tiempo - じかん','Tiempo','じかん','N5');

# codigo palabra kanji
INSERT INTO palabkanji VALUES (0,'luna','月');
INSERT INTO palabkanji VALUES (1,'mes','月');
INSERT INTO palabkanji VALUES (2,'fuego',' 火');
INSERT INTO palabkanji VALUES (3,'sol','日');
INSERT INTO palabkanji VALUES (4,'tiempo','天気');
INSERT INTO palabkanji VALUES (5,'tiempo','時間');

# codigo  enunciado
INSERT INTO enunciado VALUES (0,'Lectura de');
INSERT INTO enunciado VALUES (1,'Kanji de');
INSERT INTO enunciado VALUES (2,'Significado de');
INSERT INTO enunciado VALUES (3,'');