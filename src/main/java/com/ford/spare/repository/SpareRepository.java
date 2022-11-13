package com.ford.spare.repository;

import com.ford.spare.model.SparePart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpareRepository extends MongoRepository<SparePart, Integer> {
    List<SparePart> findAll();
    SparePart save(SparePart sparePart);
    @Query("{ 'spareId' : ?0 }")
    Optional<SparePart> findByTheId(String spareId);
    void deleteBySpareId(String spareId);
}