CREATE PROCEDURE fill_tree_node()

BEGIN
DECLARE a INT Default 1 ;

simple_loop: LOOP

insert into tree_node(name, description) values
            ("Java является", "Java является"),
            ("Java является", "Java является"),
            ("объектно-о", "объектно-о"),
            ("риентирован", "риентирован"),
            ("ным", "ным"),
            ("языком програ", "языком програ"),
            ("ммирования,", "ммирования,"),
            ("вследствие че", "ммирования"),
            ("го предварите", "ммировани"),
            ("льно буд", "ммирования"),
            ("ут пр", "ьзователя и"),
            ("ив", "ьзователя и"),
            ("едены", "ьзователя и"),
            ("ос", "ьзователя и"),
            ("новные пара", "ьзователя и"),
            ("дигмы ООП.", "ьзователя и"),
            ("В связи с п", "ьзователя и"),
            ("роникновение", "ьзователя и"),
            ("м к", "ьзователя и"),
            ("омпьютеров", "ьзователя и"),
            ("во все", "ьзователя и"),
            ("сферы социум", "ьзователя и"),
            ("а программные с", "ммирования"),
            ("истемы становятс", "ммирования"),
            ("я более", "ммирования"),
            ("прос", "ммирования"),
            ("тыми для", "ммирования"),
            ("пол", "пол"),
            ("ьзователя и", "ьзователя и"),
            ("сложными по", "ьзователя и"),
            ("внутренней", "ьзователя и"),
            ("архитектуре.", "ьзователя и"),
            ("Программи", "Программи"),
            ("рование стало", "Программи"),
            ("делом команды,", "Программи"),
            ("где маленьким", "Программи"),
            ("проектом", "Программи"),
            ("считается", "Программи"),
            ("тот,", "Программи"),
            ("который выполн", "Программи"),
            ("яет команда", "Программи"),
            ("Java является", "Программи"),
            ("объектно-о", "Программи"),
            ("риентирован", "Программи"),
            ("ным", "Программи"),
            ("языком програ", "Программи"),
            ("ммирования,", "Программи"),
            ("вследствие че", "Программи"),
            ("го предварите", "Программи"),
            ("льно буд", "Программи"),
            ("ут пр", "Программи");
SET a=a+1;
IF a=10460 THEN
LEAVE simple_loop;
END IF;
END LOOP simple_loop;

END