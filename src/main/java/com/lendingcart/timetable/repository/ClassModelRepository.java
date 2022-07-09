package com.lendingcart.timetable.repository;

import com.lendingcart.timetable.model.ClassModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassModelRepository extends MongoRepository<ClassModel,Integer> {
    @Query("{classId:'?0'}")
    Optional<ClassModel> findClassModelById(String classId);

    @Query("{classId:'?0'}")
    Optional<ClassModel> deleteClassModelById(String classId);
}
