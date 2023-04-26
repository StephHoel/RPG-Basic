package com.avanade.rpgbasic.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avanade.rpgbasic.model.GameModel;


@Repository
public interface GameRepository extends JpaRepository<GameModel, UUID> {
   
   @Query(value = "select * from games c where c.deleted = ?1", nativeQuery = true)
   List<GameModel> findAll(Boolean del);

}
