package com.avanade.rpgbasic.controller;

import com.avanade.rpgbasic.model.CharacterModel;
import com.avanade.rpgbasic.service.CharacterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
@Api(value = "RPG API REST")
@CrossOrigin(origins = "*")
public class CharacterController {

   @Autowired
   private CharacterService service;

   // create
   @PostMapping("/character")
   @ApiOperation("create a new character")
   public ResponseEntity<CharacterModel> create(@RequestBody CharacterModel character) {
      return new ResponseEntity<CharacterModel>(service.create(character), HttpStatus.CREATED);
   }

   // read
   @GetMapping("/character")
   @ApiOperation("find all chars registred")
   public ResponseEntity<List<CharacterModel>> getAll() {
      return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
   }

   @GetMapping("/character/{id}")
   @ApiOperation("find a character by it's id")
   public ResponseEntity<CharacterModel> getById(@PathVariable(value = "id") UUID id) {
      return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
   }

   // update
   @PutMapping("/character")
   @ApiOperation("update a character")
   public ResponseEntity<CharacterModel> update(@RequestBody CharacterModel characterModel) {
      return new ResponseEntity<>(service.update(characterModel), HttpStatus.OK);
   }

   // delete
   @DeleteMapping("/character")
   @ApiOperation("delete a character")
   public ResponseEntity<HttpStatus> delete(@RequestHeader UUID id) {
      CharacterModel characterModel = getById(id).getBody();
      service.delete(characterModel);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

}
