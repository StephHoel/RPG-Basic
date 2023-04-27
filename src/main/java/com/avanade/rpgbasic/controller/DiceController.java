package com.avanade.rpgbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.rpgbasic.model.CharacterModel;
import com.avanade.rpgbasic.model.GameModel;
import com.avanade.rpgbasic.service.DiceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "RPG API REST")
@CrossOrigin(origins = "*")
public class DiceController {
   @Autowired
   private DiceService service;

   @PostMapping("/attack")
   @ApiOperation("getting dice's value for attack")
   public ResponseEntity<Integer> attack(@RequestBody CharacterModel character, GameModel gameModel) {
      return new ResponseEntity<>(service.attack(character, gameModel), HttpStatus.OK);
   }

   @PostMapping("/defense")
   @ApiOperation("getting dice's value for defense")
   public ResponseEntity<Integer> defense(@RequestBody CharacterModel character, GameModel gameModel) {
      return new ResponseEntity<>(service.defense(character, gameModel), HttpStatus.OK);
   }

   @PostMapping("/damage")
   @ApiOperation("getting dice's value for damage")
   public ResponseEntity<Integer> damage(@RequestBody CharacterModel character, GameModel gameModel) {
      return new ResponseEntity<>(service.damage(character, gameModel), HttpStatus.OK);
   }
}