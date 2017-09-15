USE nihongo;

UPDATE testd SET nivel=0 WHERE codigo=0; 
UPDATE testd SET nivel=1 WHERE codigo=1; 
UPDATE testd SET nivel=0 WHERE codigo=2; 
UPDATE testd SET nivel=0 WHERE codigo=3; 
UPDATE testd SET nivel=4 WHERE codigo=4; 
UPDATE testd SET fecha='2017-06-25' WHERE codigo=1;
UPDATE testd SET fecha='2017-06-27' WHERE codigo=0;
UPDATE testd SET fecha='2017-05-10' WHERE codigo=2;
UPDATE testd SET fecha='2017-05-10' WHERE codigo=2;