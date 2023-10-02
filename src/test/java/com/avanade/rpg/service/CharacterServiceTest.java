package com.avanade.rpg.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.avanade.rpgbasic.model.CharacterModel;
import com.avanade.rpgbasic.repository.CharacterRepository;
import com.avanade.rpgbasic.service.CharacterService;

public class CharacterServiceTest {
   List<CharacterModel> list = new ArrayList<>();

   @InjectMocks
   private CharacterService service;

   @Mock
   private CharacterRepository repository;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);

      CharacterModel character1 = new CharacterModel(UUID.randomUUID(), "Char1", "Warrior", 0, true,
            LocalDateTime.now(), LocalDateTime.now(), false);
      // 1L, "Configurar Mockito", "Configurar os mocks para teste unitário", false,
      // LocalDateTime.now(), null

      CharacterModel character2 = new CharacterModel(UUID.randomUUID(), "Char2", "Orc", 0, false, LocalDateTime.now(),
            LocalDateTime.now(), false);
      // 2L, "Criar testes unitários", "Configurar chamadas de teste unitário", false,
      // LocalDateTime.now(), null

      list.add(character1);
      list.add(character2);
   }

   @Test
   void create() {

      CharacterModel character = list.get(0);

      CharacterModel expected = character;
      expected.setLife(26);

      when(repository.save(character)).thenReturn(character);

      CharacterModel response = service.create(character);

      Assertions.assertEquals(expected.getLife(), response.getLife());

      verify(repository, times(1)).save(any());
   }

   // @Test
   // void findAll() {
   // when(repository.findAll()).thenReturn(list);
   // List<Character> characters = service.findAll();
   // Assertions.assertEquals(characters, list);
   // verify(repository, times(1)).findAll();
   // }

   // @Test
   // void findById() {
   // when(repository.findById(any())).thenReturn(Optional.ofNullable(list.get(0)));
   // CharacterModel character = service.findById(1L);
   // Assertions.assertEquals(character, list.get(0));
   // verify(repository, times(1)).findById(any());
   // }

   // @Test
   // void delete() {
   // doNothing().when(repository).deleteById(any());
   // service.delete(1L);
   // verify(repository, times(1)).deleteById(any());
   // }

   // @Test
   // void update() {
   // CharacterModel character = list.get(0);
   // character.setIsCompleted(true);
   // character.setCompletedAt(LocalDateTime.now());
   // when(repository.save(character)).thenReturn(character);
   // CharacterModel response = service.update(character);
   // Assertions.assertEquals(character.getIsCompleted(),
   // response.getIsCompleted());
   // verify(repository, times(1)).save(any());
   // }
}
