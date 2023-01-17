-- Возраст студента не может быть меньше 16 лет.
ALTER TABLE student
    ADD CONSTRAINT age_student CHECK (age >= 16);

-- Имена студентов должны быть уникальными.
ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE (name);

-- Имена студентов не должны быть равны нулю.
ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

-- Пара “значение названия” - “цвет факультета” должна быть уникальной.
ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);

-- При создании студента без возраста ему автоматически должно присваиваться 20 лет.
ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;