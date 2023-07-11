package org.example.repositories;

import org.example.models.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CoursesRepositorySpringJdbcImpl implements CoursesRepository {

    private final DataSource dataSource;
    private static final String COURSE_TABLE = "course";
    private final SimpleJdbcInsert insertCourse;
    private final JdbcTemplate jdbcTemplate;
    private final static String SQL_SELECT_ALL = "select * from course";

    public CoursesRepositorySpringJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;

        jdbcTemplate = new JdbcTemplate(dataSource);

        insertCourse = new SimpleJdbcInsert(dataSource);

        insertCourse.withTableName(COURSE_TABLE)
                .usingGeneratedKeyColumns("id");
    }

    private static final Function<Course, Map<String, Object>> toParams = course -> {
        Map<String, Object> params = new HashMap<>();

        params.put("title", course.getTitle());
        params.put("start_date", course.getStartDate());
        params.put("finish_date", course.getFinishDate());
        return params;
    };
    @Override
    public void save(Course model) {

        int generatedID = insertCourse.executeAndReturnKey(toParams.apply(model)).intValue();

        model.setId((generatedID));
    }

    private static final RowMapper<Course> toCourse = (row, rowNumber)  -> Course.builder()
            .id(row.getInt("id"))
            .title(row.getString("title"))
            .startDate(LocalDate.parse(row.getString("start_date")))
            .finishDate(LocalDate.parse(row.getString("finish_date")))
            .build();



    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, toCourse);
    }
}

