select *
from student;

-- Получить всех студентов, возраст которых находится между 10 и 20.
select *
from student
where age > 10
  and age < 20;

-- Получить имена всех студентов.
select student.name
from student;

-- Получить студентов, у которых в имени есть буква "а".
select *
from student
where name like '%a%';

-- Получить всех студентов, у которых возраст меньше идентификатора.
select *
from student
where age < student.id;

-- Получить студентов отсортированных по возрасту.
select *
from student
order by age;

select f.name, f.color, s.name
from student as s,
     faculty as f
where s.faculty_id = f.id
  and f.id = 3;