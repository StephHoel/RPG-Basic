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
@Table(name = "LOGS")
@Entity
public class LogModel implements Serializable {
   @Id
   @Column(name = "ID_LOG", nullable = false, unique = true)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID idLog;
   @Column(name = "ID_CHARACTER", nullable = false)
   private UUID idCharacter;
   @Column(name = "ID_GAME")
   private UUID idGame;
   // número do turno ; 0 para criação de personagem
   @Column(name = "TURN", nullable = false)
   private Integer turn;
   // exemplo: ataque, defesa, criação, início de batalha, vitória
   @Column(name = "ACTION", nullable = false)
   private String action;
   // ex: fulano atacou com dado x | ciclano defendeu com dado y
   @Column(name = "DESCRIPTION", nullable = false)
   private String description;
   @Column(name = "CREATED_AT", nullable = false)
   private LocalDateTime createdAt;
   @Column(name = "UPDATED_AT", nullable = false)
   private LocalDateTime updatedAt;
   @Column(name="IS_DELETED", nullable = false)
   private Boolean isDeleted = false; // ao deletar, alterar updatedAt

}
