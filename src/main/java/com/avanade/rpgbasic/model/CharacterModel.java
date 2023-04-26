package com.avanade.rpgbasic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHARACTERS")
@Entity
public class CharacterModel implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "ID_CHAR", nullable = false)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID idCharacter;
   @Column(name = "NAME", nullable = false)
   private String name;
   @Column(name = "RACE", nullable = false)
   private String race;
   @Column(name = "LIFE", nullable = false)
   private Integer life;
   @Column(name = "PLAYABLE", nullable = false)
   private Boolean playable;
   @Column(name = "CREATED_AT", nullable = false)
   private LocalDateTime createdAt;
   @Column(name = "UPDATED_AT", nullable = false)
   private LocalDateTime updatedAt;
   @Column(name = "IS_DELETED", nullable = false)
   private Boolean isDeleted;

}
