package com.lendingcart.timetable.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDataRequest {
    private String teacherName;
    private String subjectName;
    private String classId;
}
