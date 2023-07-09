package org.example.repositories;

import org.example.models.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryJdbcImpl implements StudentRepository{
    private final DataSource dataSource;
    //language=sql
    private final static String SQL_SELECT_ALL = "select * from student ";
    //language=sql
    private final static String SQL_INSERT = "insert into student(first_name, last_name, age) values (?, ?, ?)";

    public StudentRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Student model) {
        try(Connection connection = dataSource.getConnection(); ){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setInt(3, model.getAge());

            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Cannot insert");
            }
            try(ResultSet generatedIds =  statement.getGeneratedKeys()){
                if (generatedIds.next()) {
                    model.setId(generatedIds.getInt("id"));
                } else {
                    throw new SQLException("Cannot revive id");
                }
            }
        } catch(SQLException e){
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)){
                    while (resultSet.next()) {
                        Student student = Student.builder()
                                .id(resultSet.getInt("id"))
                                .firstName(resultSet.getString("first_name"))
                                .lastName(resultSet.getString("last_name"))
                                .age(resultSet.getInt("age"))
                                .build();
                        students.add(student);
                    }
            }
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return students;
    }

}
