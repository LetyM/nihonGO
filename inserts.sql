USE nihongo;
#Para conectar con java. Al parecer GRANT está desfasado
#ALTER USER 'java'@'localhost' IDENTIFIED BY 'password';
#GRANT ALL ON nihongo.* TO 'java'@'localhost' IDENTIFIED BY 'password';

#INSERT INTO usuarios VALUES ('Pepe', 'pepe@gmail.com', 'pepejapon');
#INSERT INTO lecciones VALUES ('1', '5', '2');
#INSERT INTO vocabulario VALUES ('001','yo','わたし','N5');
#INSERT INTO gramatica VALUES ('001','Querer~','~たい','N5');
INSERT INTO kanji VALUES ('0','月','つき','ゲツ ガツ','N5','10');
INSERT INTO kanji VALUES ('1','日','ひ','ニチ　ジツ','N5','10');
INSERT INTO test VALUES (0,'Kunyomi de 月','つき','ひ','つさ',1,0,'2017-05-10 12:00:00');
