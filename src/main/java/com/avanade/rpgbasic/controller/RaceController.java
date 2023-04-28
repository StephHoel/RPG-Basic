package com.avanade.rpgbasic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.avanade.rpgbasic.model.RaceModel;
import com.avanade.rpgbasic.service.RaceService;

@RestController
@RequestMapping(value = "/api")
@Api(value = "RPG API REST")
@CrossOrigin(origins = "*")
public class RaceController {
   @Autowired
   private RaceService service;

   @GetMapping("/race/{race}")
   @ApiOperation("Find a race's informations by race")
   public ResponseEntity<RaceModel> getByRace(@PathVariable(value = "race") String race) {
      return new ResponseEntity<>(service.findById(race), HttpStatus.OK);
   }

   @GetMapping("/race")
   @ApiOperation("Find a race's informations by race")
   public ResponseEntity<List<RaceModel>> getAll() {
      return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
   }

}
