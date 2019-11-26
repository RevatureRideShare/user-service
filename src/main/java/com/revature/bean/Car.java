package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 * This object's purpose is to hold a car. Its fields consist of: carID: a car id in which the
 * object can be identified with this id; seatNumber: this field is used to hold the number of seats
 * a car can have.
 * 
 * @author Jane Shin
 * @author Erik Haklar
 * @author Roberto Rodriguez
 */
@Entity
@Table(name = "car")
public class Car {

  @Id
  @SequenceGenerator(name = "CI_SEQ", sequenceName = "car_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CI_SEQ")
  @Column(name = "car_id")
  private int carID;

  @Column(name = "user_id")
  int userID;

  @Column(name = "seats")
  @NotEmpty
  @Positive
  @Min(2)
  @Max(50)
  private int seatNumber;

  public Car() {
    super();

  }

  /**
   * This Car constructor uses the below parameters.
   * 
   * @param carID The car id in which the car can be grabbed by
   * @param seatNumber The number of seats in the car
   */
  public Car(int carID, @NotEmpty @Positive @Min(2) @Max(50) int seatNumber) {
    super();
    this.carID = carID;
    this.seatNumber = seatNumber;
  }

  public int getCarID() {
    return carID;
  }

  public void setCarID(int carID) {
    this.carID = carID;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + carID;
    result = prime * result + seatNumber;
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Car other = (Car) obj;
    if (carID != other.carID)
      return false;
    if (seatNumber != other.seatNumber)
      return false;
    return true;
  }


  @Override
  public String toString() {
    return "Car [carID=" + carID + ", seatNumber=" + seatNumber + "]";
  }

}
