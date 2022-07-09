package com.lendingcart.timetable.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassModel {
    @Id
    private String classId;
    private Integer classNumber;
    private List<Subject> subjectList;
}
