package com.avanade.rpgbasic.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avanade.rpgbasic.model.CharacterModel;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, UUID> {

   @Query(value = "select * from characters c where c.deleted = ?1", nativeQuery = true)
   List<CharacterModel> findAll(Boolean del);
}
