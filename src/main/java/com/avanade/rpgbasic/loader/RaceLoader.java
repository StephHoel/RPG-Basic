package com.avanade.rpgbasic.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.avanade.rpgbasic.model.RaceModel;
import com.avanade.rpgbasic.repository.RaceRepository;


@Component
public class RaceLoader implements ApplicationRunner {

   @Autowired
   private RaceRepository raceRepository;

   public void run(ApplicationArguments args) {
      raceRepository.save(new RaceModel("Warrior", 20, 7, 5, 6, 1, 12));
      raceRepository.save(new RaceModel("Barbarian", 21, 10, 2, 5, 2, 8));
      raceRepository.save(new RaceModel("Knight", 26, 6, 8, 3, 2, 6));
      raceRepository.save(new RaceModel("Orc", 42, 7, 1, 2, 3, 4));
      raceRepository.save(new RaceModel("Giant", 34, 10, 4, 4, 2, 6));
      raceRepository.save(new RaceModel("Werewolf", 34, 7, 4, 7, 2, 4));
   }

}
