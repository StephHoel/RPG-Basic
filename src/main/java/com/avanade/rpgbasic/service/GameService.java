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

   @Autowired
   private LogService logService;

   // create
   public GameModel create(GameModel gameModel) {
      gameModel.setCreatedAt(LocalDateTime.now());
      gameModel.setUpdatedAt(LocalDateTime.now());
      gameModel.setIsDeleted(false);
      gameModel.setIsFinished(false);
      
      // Saving LOG
      logService.logGame(gameModel.getIdGame(), "Create", "Create Game with ID: " + gameModel.getIdGame());

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
      
      // Saving LOG
      logService.logGame(gameModel.getIdGame(), "Update", "Update Game with ID: " + gameModel.getIdGame());

      return repository.save(gameModel);
   }

   public GameModel finish(GameModel gameModel) {
      if (gameModel.getIdGame() == null) {
         throw new InvalidInput("There is no ID");
      }

      gameModel.setUpdatedAt(LocalDateTime.now());
      gameModel.setIsFinished(true);
      
      // Saving LOG
      logService.logGame(gameModel.getIdGame(), "Finished", "Finished Game with ID: " + gameModel.getIdGame());

      return repository.save(gameModel);
   }

   // delete
   public void delete(GameModel gameModel) {
      if (gameModel.getIdGame() == null) {
         throw new InvalidInput("There is no ID");
      }

      gameModel.setUpdatedAt(LocalDateTime.now());
      gameModel.setIsDeleted(true);
      
      // Saving LOG
      logService.logGame(gameModel.getIdGame(), "Delete", "Delete Game with ID: " + gameModel.getIdGame());

      repository.save(gameModel);
   }

}
