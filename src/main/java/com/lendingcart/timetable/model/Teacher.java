package com.lendingcart.timetable.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "teacher")
public class Teacher {
    @Id
    private String teacherId;
    private String teacherName;
    private Subject subject;
    private String classId;
}
