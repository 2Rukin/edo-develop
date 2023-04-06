INSERT INTO theme (name,creation_date, code)
VALUES ('Вопросы по благоустройству', now() ,'1'),
       ('Благустройство парков',now() , '1.1'),
       ('Благоустройство улиц', now() ,'1.2'),
       ('Пешеходная часть',now() , '1.2.1'),
       ('Автомобильная часть',now() , '1.2.2'),
       ('Благоустройство дворов',now() , '1.3'),
       ('Вопросы по работе коммунальных служб',now() , '2'),
       ('Обращение с тко',now() , '2.1'),
       ('Содержание придомовых территорий',now() , '2.2'),
       ('Содержание коммунальных сетей',now() , '2.3'),
       ('Вопросы по социальным льготам',now() , '3'),
       ('Социальные льготы для многодетных семей',now() , '3.1'),
       ('Социальные льготы для ивалидов',now() , '3.2'),
       ('Соцаильные льготы для пенсионеров',now() , '3.3');

UPDATE theme
SET parent_theme = (SELECT id FROM theme WHERE code='1')
WHERE code IN ('1.1','1.2','1.3');

UPDATE theme
SET parent_theme = (SELECT id FROM theme WHERE code='1.2')
WHERE code IN ('1.2.1', '1.2.2');

UPDATE theme
SET parent_theme = (SELECT id FROM theme WHERE code='2')
WHERE code IN ('2.1', '2.2', '2.3');

UPDATE theme
SET parent_theme = (SELECT id FROM theme WHERE code='3')
WHERE code IN ('3.1','3.2','3.3');