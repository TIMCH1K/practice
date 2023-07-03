package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
@Data // toString, Getter/Setter, equals, hashCode
@AllArgsConstructor // конструктор со всеми параметрами
@NoArgsConstructor // конструктор без параметров
@Builder // специальный функционал для удобного создания объектов
public class User {
    private String id;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "ID: "+ id + ", email: " + email + ", password: " + password;
    }
}
