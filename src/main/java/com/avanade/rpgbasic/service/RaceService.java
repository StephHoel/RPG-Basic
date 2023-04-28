package com.avanade.rpgbasic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.rpgbasic.exception.RaceNotFound;
import com.avanade.rpgbasic.model.RaceModel;
import com.avanade.rpgbasic.repository.RaceRepository;

@Service
public class RaceService {
   @Autowired
   private RaceRepository repository;

   public RaceModel findById(String race) {
      return repository.findById(race)
            .orElseThrow(() -> new RaceNotFound("Race \"" + race + "\" not found"));
   }
   
   public List<RaceModel> findAll() {
      return repository.findAll();
   }

}
