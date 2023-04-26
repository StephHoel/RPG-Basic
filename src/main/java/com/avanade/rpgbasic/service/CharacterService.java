package com.avanade.rpgbasic.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.avanade.rpgbasic.exception.InvalidInput;
import com.avanade.rpgbasic.exception.ResourceNotFound;
import com.avanade.rpgbasic.model.CharacterModel;
import com.avanade.rpgbasic.model.RaceModel;
import com.avanade.rpgbasic.repository.CharacterRepository;
import com.google.gson.Gson;

@Service
public class CharacterService {
   @Autowired
   private CharacterRepository repository;

   @Value("${api.url.race}")
   private String uri;

   // create
   public CharacterModel create(CharacterModel character) {
      character.setCreatedAt(LocalDateTime.now());
      character.setUpdatedAt(LocalDateTime.now());
      character.setIsDeleted(false);

      RaceModel raceModel = null;
      String race = character.getRace();

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
      } else {
         character.setLife(raceModel.getLifeInitial());
      }

      return this.repository.save(character);
   }

   // read
   public List<CharacterModel> findAll() {
      return repository.findAll(false);
   }

   public CharacterModel findById(UUID id) {
      return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Character not found with ID: " + id));
   }

   // update
   public CharacterModel update(CharacterModel characterModel) {
      if (characterModel.getIdCharacter() == null) {
         throw new InvalidInput("There is no ID");
      }

      characterModel.setUpdatedAt(LocalDateTime.now());
      return repository.save(characterModel);
   }

   // delete
   public void delete(CharacterModel characterModel) {
      if (characterModel.getIdCharacter() == null) {
         throw new InvalidInput("There is no ID");
      }

      characterModel.setUpdatedAt(LocalDateTime.now());
      characterModel.setIsDeleted(true);
      repository.save(characterModel);
   }

}
