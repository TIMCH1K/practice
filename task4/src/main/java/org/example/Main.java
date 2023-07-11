package org.example;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.models.Course;
import org.example.models.Student;
import org.example.repositories.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/Student");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("pKMFr7CW");
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        StudentRepository studentsRepository = new StudentsRepositorySpringJdbcImpl(dataSource);
        System.out.println(studentsRepository.findAll());
        Student student = Student.builder()
                .firstName("Джон")
                .lastName("Каллиган")
                .age(25)
                .build();
        System.out.println(student);
        studentsRepository.save(student);
        System.out.println(student);

        CoursesRepository coursesRepository = new CoursesRepositorySpringJdbcImpl(dataSource);
        System.out.println(coursesRepository.findAll());

        LocalDate startDate = LocalDate.of(2023, 2, 9);
        LocalDate finishDate = LocalDate.of(2023, 6, 3);
        Course course = Course.builder()
                .title("АиСД")
                .startDate(startDate)
                .finishDate(finishDate)
                .build();
        System.out.println(course);
        coursesRepository.save(course);
        System.out.println(course);

    }
}