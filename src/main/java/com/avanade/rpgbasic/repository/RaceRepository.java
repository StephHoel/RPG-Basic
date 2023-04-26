package com.avanade.rpgbasic.repository;

import com.avanade.rpgbasic.model.RaceModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<RaceModel, String> {

}