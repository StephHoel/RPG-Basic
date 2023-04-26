package com.avanade.rpgbasic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.rpgbasic.exception.InvalidInput;
import com.avanade.rpgbasic.exception.ResourceNotFound;
import com.avanade.rpgbasic.model.GameModel;
import com.avanade.rpgbasic.repository.GameRepository;

@Service
public class GameService {
   @Autowired
   private GameRepository repository;

   // create
   public GameModel create(GameModel gameModel) {
      gameModel.setCreatedAt(LocalDateTime.now());
      gameModel.setUpdatedAt(LocalDateTime.now());
      gameModel.setIsDeleted(false);
      gameModel.setIsFinished(false);

      return this.repository.save(gameModel);
   }

   // read
   public List<GameModel> findAll() {
      return repository.findAll(false);
   }

   public GameModel findById(UUID id) {
      return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Game not found with ID: " + id));
   }

   // update
   public GameModel update(GameModel gameModel) {
      if (gameModel.getIdGame() == null) {
         throw new InvalidInput("There is no ID");
      }

      gameModel.setUpdatedAt(LocalDateTime.now());
      return repository.save(gameModel);
   }

   public GameModel finish(GameModel gameModel) {
      if (gameModel.getIdGame() == null) {
         throw new InvalidInput("There is no ID");
      }

      gameModel.setUpdatedAt(LocalDateTime.now());
      gameModel.setIsFinished(true);
      return repository.save(gameModel);
   }

   // delete
   public void delete(GameModel gameModel) {
      if (gameModel.getIdGame() == null) {
         throw new InvalidInput("There is no ID");
      }

      gameModel.setUpdatedAt(LocalDateTime.now());
      gameModel.setIsDeleted(true);

      repository.save(gameModel);
   }

}
