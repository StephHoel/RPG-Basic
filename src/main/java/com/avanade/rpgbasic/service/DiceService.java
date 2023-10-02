package com.avanade.rpgbasic.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.avanade.rpgbasic.model.CharacterModel;
import com.avanade.rpgbasic.model.GameModel;
import com.avanade.rpgbasic.model.RaceModel;
import com.google.gson.Gson;

@Service
public class DiceService {
   @Autowired
   private LogService logService;

   private String uri = "http://localhost:8080/api/race";

   public UUID init(GameModel gameModel) {
      int init1, init2;

      UUID char1 = gameModel.getIdCharacter1();
      UUID char2 = gameModel.getIdCharacter2();
      UUID win;

      do {
         init1 = dice(1, 20);
         init2 = dice(1, 20);
      } while (init1 != init2);

      // Saving LOG
      logService.logDice(char1, gameModel, "Init's Dice",
            "Init's Dice: Character with ID " + char1
                  + " in Game with ID " + gameModel.getIdGame()
                  + " rolled " + init1 + " in dice");
      logService.logDice(char2, gameModel, "Init's Dice",
            "Init's Dice: Character with ID " + char2
                  + " in Game with ID " + gameModel.getIdGame()
                  + " rolled " + init2 + " in dice");

      if (init1 > init2) {
         win = char1;
         logService.logDice(char1, gameModel, "Start Game",
               "Game with ID " + gameModel.getIdGame()
                     + " starting with the Character with ID " + char1
                     + " that rolled " + init1 + " in dice");
      } else {
         win = char2;
         logService.logDice(char2, gameModel, "Start Game",
               "Game with ID " + gameModel.getIdGame()
                     + " starting with the Character with ID " + char2
                     + " that rolled " + init2 + " in dice");
      }

      return win;
   }

   public Integer attack(CharacterModel character, GameModel gameModel) {
      String race = character.getRace();
      RaceModel raceModel = raceModel(race);

      int attack = dice(1, 12) + raceModel.getStrong()
            + raceModel.getAgility();

      // Saving LOG
      logService.logDice(character.getIdCharacter(), gameModel, "Attack's Dice",
            "Attack's Dice: Character with ID " + character.getIdCharacter()
                  + " in Game with ID " + gameModel.getIdGame()
                  + " with " + attack + " in dice");

      return attack;
   }

   public Integer defense(CharacterModel character, GameModel gameModel) {
      String race = character.getRace();
      RaceModel raceModel = raceModel(race);

      int defense = dice(1, 12) + raceModel.getDefense()
            + raceModel.getAgility();

      // Saving LOG
      logService.logDice(character.getIdCharacter(), gameModel, "Defense's Dice",
            "Defense's Dice: Character with ID " + character.getIdCharacter()
                  + " in Game with ID " + gameModel.getIdGame()
                  + " with " + defense + " in dice");

      return defense;
   }

   public Integer damage(CharacterModel characterAttack, GameModel gameModel) {
      String race = characterAttack.getRace();
      RaceModel raceModel = raceModel(race);

      UUID idOtherChar = gameModel.getIdCharacter1() == characterAttack.getIdCharacter()
            ? gameModel.getIdCharacter1()
            : gameModel.getIdCharacter2();

      int dice = dice(raceModel.getDiceQuantity(), raceModel.getDiceFace());

      // Saving LOG
      logService.logDice(characterAttack.getIdCharacter(), gameModel, "Damage's Dice",
            "Damage's Dice: Character with ID " + characterAttack.getIdCharacter()
                  + " damaged the Character with ID " + idOtherChar + " in Game with ID " + gameModel.getIdGame()
                  + " with " + dice + " in dice");

      return dice;

   }

   // support methods
   private RaceModel raceModel(String race) {

      RaceModel raceModel = null;

      try {
         uri = uri + "/" + race;
         URL url = new URL(uri);

         HttpURLConnection conn = (HttpURLConnection) url.openConnection();

         conn.setRequestMethod("GET");
         conn.setRequestProperty("User-Agent", "Mozilla/5.0");

         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

         String inputLine;
         StringBuffer response = new StringBuffer();

         while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
         }
         in.close();

         Gson gson = new Gson();
         raceModel = gson.fromJson(response.toString(), RaceModel.class);

      } catch (Exception ex) {
         ex.printStackTrace();
      }

      if (raceModel == null) {
         throw new RestClientException("Invalid response from API");
         // character.setLife(0);
      }

      return raceModel;
   }

   private Integer dice(int qtd, int faces) {

      // define the range
      int min = 1;
      int range = faces - min + 1;
      int sum = 0;

      for (int i = 0; i < qtd; i++) {
         sum += (int) (Math.random() * range);
      }

      return sum;
   }

}