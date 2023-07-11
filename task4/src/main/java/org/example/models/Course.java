package org.example.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    private Integer id;
    private String title;
    private LocalDate startDate;
    private LocalDate finishDate;
}
