-- Описание структуры: у каждого человека есть машина.
-- Причем несколько человек могут пользоваться одной машиной.
-- У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
-- У каждой машины есть марка, модель и стоимость.
-- Также не забудьте добавить таблицам первичные ключи и связать их.

CREATE TABLE car
(
    id    BIGSERIAL PRIMARY KEY,
    brand VARCHAR(255),
    model VARCHAR(255),
    price DECIMAL
);

CREATE TABLE human
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255),
    age     INTEGER,
    license BOOLEAN,
    car_id  BIGINT,
    FOREIGN KEY (car_id) REFERENCES car (id)
);