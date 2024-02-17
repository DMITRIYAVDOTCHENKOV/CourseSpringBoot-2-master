CREATE TABLE IF NOT EXISTS "user"
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    age  int NOT NULL,
    email varchar(50) not null
);

INSERT INTO "user" (name, age, email) values ('Ivan', 25, 'ivan@mail.ru');
INSERT INTO "user" (name, age, email) values ('Sergey', 28, 'serg@mail.ru');
INSERT INTO "user" (name, age, email) values ('Fedor', 22, 'fedor@mail.ru');