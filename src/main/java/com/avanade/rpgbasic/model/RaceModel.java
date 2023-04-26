package com.avanade.rpgbasic.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RACES")
@Entity
public class RaceModel implements Serializable {   
   @Id
   @Column(name = "RACE", nullable = false, unique = true)
   private String race;
   @Column(name = "LIFE_INITIAL", nullable = false)
   private Integer lifeInitial;
   @Column(name = "STRONG", nullable = false)
   private Integer strong;
   @Column(name = "DEFENSE", nullable = false)
   private Integer defense;
   @Column(name = "AGILITY", nullable = false)
   private Integer agility;
   @Column(name = "DICE_QUANTITY", nullable = false)
   private Integer diceQuantity;
   @Column(name = "DICE_FACE", nullable = false)
   private Integer diceFace;

   
}
