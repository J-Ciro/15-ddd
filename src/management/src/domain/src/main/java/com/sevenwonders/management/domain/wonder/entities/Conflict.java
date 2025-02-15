package com.sevenwonders.management.domain.wonder.entities;


import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.wonder.values.ConflictId;
import com.sevenwonders.management.domain.wonder.values.Location;
import com.sevenwonders.management.domain.wonder.values.Marks;
import com.sevenwonders.shared.domain.generic.Entity;

import java.util.ArrayList;
import java.util.List;

public class Conflict extends Entity<ConflictId> {

 private Marks marks;
 private Shields shields;
 private Location location;


 public Conflict(ConflictId identity, Marks marks, Shields shields, Location location) {
   super(identity);
   this.marks = marks;
   this.shields = shields;
   this.location = location;
 }

 public Conflict(Marks marks, Shields shields, Location location) {
   super(new ConflictId());
   this.marks = marks;
   this.shields = shields;
   this.location = location;
 }



 // region Methods

  public Integer calculateBattlePoints(Integer shields, Integer currentEra, Integer enemyShields) {
    int victoryPoints = switch (currentEra) {
      case 1 -> 1;
      case 2 -> 3;
      case 3 -> 5;
      default -> throw new IllegalArgumentException("Invalid era: " + currentEra);
    };

    int markValue;
    if (shields > enemyShields) {
      markValue = victoryPoints;
    } else if (shields < enemyShields) {
      markValue = -1;
    } else {
      markValue = 0;
    }

    List<Integer> updatedMarks = new ArrayList<>(this.marks.getValue());
    updatedMarks.add(markValue);

    this.marks = Marks.of(updatedMarks);

    return markValue;
  }


  public String updateLocation(Integer victoryMark, Integer loserMark, String enemyLocation) {
      if (victoryMark > 0 && (enemyLocation.equals("left") || enemyLocation.equals("right"))) {
        this.location = Location.of(enemyLocation);
        return "Victory location updated: " + enemyLocation;
      } else if (loserMark < 0 && (enemyLocation.equals("left") || enemyLocation.equals("right"))) {
        this.location = Location.of(enemyLocation);
        return "Defeat location updated: " + enemyLocation;
      }
      return "Marks updated: Victory Mark - " + victoryMark + ", Loser Mark - " + loserMark;
  }


  public Integer calculateScore(List<Integer> marks) {
    int totalScore = 0;
    for (Integer mark : marks) {
      totalScore += mark;
    }
    return totalScore;
  }

 //endregion


// region Getters and Setters
  public Marks getMarks() {
    return marks;
  }

  public void setMarks(Marks marks) {
    this.marks = marks;
  }

  public Shields getShields() {
    return shields;
  }

  public void setShields(Shields shields) {
    this.shields = shields;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  //endregion
}
