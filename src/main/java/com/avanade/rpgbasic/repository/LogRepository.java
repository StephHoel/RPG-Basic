package com.avanade.rpgbasic.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avanade.rpgbasic.model.LogModel;


@Repository
public interface LogRepository extends JpaRepository<LogModel, UUID> {
   
   @Query(value = "select * from logs c where c.deleted = ?1", nativeQuery = true)
   List<LogModel> findAll(Boolean del);
}
