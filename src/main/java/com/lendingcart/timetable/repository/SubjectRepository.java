package com.lendingcart.timetable.repository;

import com.lendingcart.timetable.model.Subject;
import com.lendingcart.timetable.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends MongoRepository<Subject,Integer> {
    @Query("{subjectId:'?0'}")
    Optional<Subject> findSubjectById(String subjectId);

    @Query("{subjectName:{$regex:?0}}")
    List<Subject> findSubjectByName(String subjectName);
}
