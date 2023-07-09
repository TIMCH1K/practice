package org.example.repositories;

import org.example.models.Course;
import org.example.models.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryJdbcImpl implements CoursesRepository{

    private final DataSource dataSource;
    //language=sql
    private final static String SQL_SELECT_ALL = "select * from course";
    private final static String SQL_INSERT = "insert into course(title, start_date, finish_date) values (?, ?, ?)";


    public CourseRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Course model) {
        try(Connection connection = dataSource.getConnection(); ){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getTitle());
            statement.setDate(2, Date.valueOf(model.getStartDate()));
            statement.setDate(3, Date.valueOf(model.getFinishDate()));

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
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)){
                    while (resultSet.next()) {
                        Course course = Course.builder()
                                .id(resultSet.getInt("id"))
                                .title(resultSet.getString("title"))
                                .startDate(LocalDate.parse(resultSet.getString("start_date")))
                                .finishDate(LocalDate.parse(resultSet.getString("finish_date")))
                                .build();
                        courses.add(course);
                }
            }
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return courses;
    }
    }

