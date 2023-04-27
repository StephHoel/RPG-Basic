package com.avanade.rpgbasic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.rpgbasic.exception.InvalidInput;
import com.avanade.rpgbasic.exception.ResourceNotFound;
import com.avanade.rpgbasic.model.GameModel;
import com.avanade.rpgbasic.model.LogModel;
import com.avanade.rpgbasic.repository.LogRepository;

@Service
public class LogService {
   @Autowired
   private LogRepository repository;

   // create
   public LogModel create(LogModel log) {
      log.setCreatedAt(LocalDateTime.now());
      log.setUpdatedAt(LocalDateTime.now());
      log.setIsDeleted(false);

      return this.repository.save(log);
   }

   // read
   public List<LogModel> findAll() {
      return repository.findAll(false);
   }

   public LogModel findById(UUID id) {
      return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Log not found with ID: " + id));
   }

   // update
   public LogModel update(LogModel logModel) {
      if (logModel.getIdLog() == null) {
         throw new InvalidInput("There is no ID");
      }

      logModel.setUpdatedAt(LocalDateTime.now());

      return repository.save(logModel);
   }

   // delete
   public void delete(LogModel logModel) {
      if (logModel.getIdLog() == null) {
         throw new InvalidInput("There is no ID");
      }

      logModel.setUpdatedAt(LocalDateTime.now());
      logModel.setIsDeleted(true);

      repository.save(logModel);
   }

   // others
   public void logGame(UUID gameId, String gameAction, String gameDescription) {
      LogModel logModel = new LogModel();
      logModel.setIdGame(gameId);
      logModel.setTurn(0);
      logModel.setAction(gameAction);
      logModel.setDescription(gameDescription);
      create(logModel);
   }

   public void logCharacter(UUID characterId, String characterAction, String characterDescription) {
      LogModel logModel = new LogModel();
      logModel.setIdCharacter(characterId);
      logModel.setTurn(0);
      logModel.setAction(characterAction);
      logModel.setDescription(characterDescription);
      create(logModel);
   }

   public void logDice(UUID idChar, GameModel gameModel, String action, String description) {
      LogModel logModel = new LogModel();
      logModel.setIdCharacter(idChar);
      logModel.setIdGame(gameModel.getIdGame());
      logModel.setTurn(gameModel.getTurnsTotal());
      logModel.setAction(action);
      logModel.setDescription(description);
      create(logModel);
   }

}
