create table student
(
    id         serial primary key, -- идентификатор строки, уникальный, генерируется автоматически базой данных
    first_name varchar(20),
    last_name  varchar(20),
    age        integer check ( age > 18 and age < 120)
);

create table course
(
    id          serial primary key,
    title       varchar(20),
    start_date  date,
    finish_date date
);

create table lesson
(
    id          serial primary key,
    name        varchar(20),
    start_time  time,
    finish_time time,
    course_id   integer,
    foreign key (course_id) references course (id) -- внешний ключ, ссылка на другую таблицу
);

create table student_course
(
    student_id integer,
    course_id  integer,
    foreign key (course_id) references course (id),
    foreign key (student_id) references student (id)
);
