package com.avanade.rpgbasic.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.rpgbasic.model.GameModel;
import com.avanade.rpgbasic.service.GameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "RPG API REST")
@CrossOrigin(origins = "*")
public class GameController {
   @Autowired
   private GameService service;

   // create
   @PostMapping("/game")
   @ApiOperation("create a new game")
   public ResponseEntity<GameModel> create(@RequestBody GameModel gameModel) {
      return new ResponseEntity<GameModel>(service.create(gameModel), HttpStatus.CREATED);
   }

   // read
   @GetMapping("/game")
   @ApiOperation("find all games registred")
   public ResponseEntity<List<GameModel>> getAll() {
      return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
   }

   @GetMapping("/game/{id}")
   @ApiOperation("find a game by it's id")
   public ResponseEntity<GameModel> getById(@PathVariable(value = "id") UUID id) {
      return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
   }

   // update
   @PutMapping("/game")
   @ApiOperation("update a game")
   public ResponseEntity<GameModel> update(@RequestBody GameModel gameModel) {
      return new ResponseEntity<>(service.update(gameModel), HttpStatus.OK);
   }

   // delete
   @DeleteMapping("/game")
   @ApiOperation("delete a game")
   public ResponseEntity<HttpStatus> delete(@RequestHeader UUID id) {
      GameModel gameModel = getById(id).getBody();
      service.delete(gameModel);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

}
