package com.avanade.rpgbasic.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GAMES")
@Entity
public class GameModel implements Serializable {
   @Id
   @Column(name = "ID_GAME", nullable = false, unique = true)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID idGame;
   @Column(name = "ID_CHARACTER1", nullable = false)
   private UUID idCharacter1;
   @Column(name = "ID_CHARACTER2", nullable = false)
   private UUID idCharacter2;
   @Column(name = "TURNS_TOTAL")
   private Integer turnsTotal;
   @Column(name = "CREATED_AT", nullable = false)
   private LocalDateTime createdAt;
   @Column(name = "UPDATED_AT", nullable = false)
   private LocalDateTime updatedAt;
   @Column(name="IS_FINISHED", nullable = false)
   private Boolean isFinished; // ao finalizar, alterar turnsTotal e updatedAt
   @Column(name="IS_DELETED", nullable = false)
   private Boolean isDeleted = false;

}