package com.lendingcart.timetable.repository;

import com.lendingcart.timetable.model.ClassModel;
import com.lendingcart.timetable.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher,Integer> {
    @Query("{teacherId:'?0'}")
    Optional<Teacher> findTeacherById(String teacherId);
}
