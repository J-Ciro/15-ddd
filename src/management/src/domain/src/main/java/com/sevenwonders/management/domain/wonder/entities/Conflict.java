package com.sevenwonders.management.domain.wonder.entities;


import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.wonder.values.ConflictId;
import com.sevenwonders.management.domain.wonder.values.Location;
import com.sevenwonders.management.domain.wonder.values.Marks;
import com.sevenwonders.shared.domain.generic.Entity;

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
}
