insert into student(first_name, last_name, age)
values ('Marsel', 'Sidikov', 29);
insert into student(first_name, last_name, age)
values ('Maxim', 'Pozdeev', 24);
insert into student(first_name, last_name, age)
values ('Viktor', 'Evlampiev', 28);

insert into course(title, start_date, finish_date)
values ('Java', '2022-02-02', '2023-02-02');
insert into course(title, start_date, finish_date)
values ('SQL', '2022-05-06', '2023-10-11');
insert into course(title, start_date, finish_date)
values ('Python', '2022-11-02', '2023-11-07');

insert into lesson (name, start_time, finish_time, course_id)
values ('Generics', '12:00', '13:00', 1),
       ('Object', '13:00', '14:00', 1),
       ('Create table', '15:00', '16:00', 2),
       ('Lists', '13:00', '16:00', 3);

insert into student_course(student_id, course_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 3),
       (3, 1),
       (3, 3);
