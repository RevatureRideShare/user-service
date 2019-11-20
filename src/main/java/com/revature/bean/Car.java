package com.revature.bean;

import java.util.UUID;

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

 //! Authors: Jane Shin, Erik Haklar, Roberto Rodriguez
 //! This object's purpose is to hold a car. Its fields consist of:
 //! carID: a car id in which the object can be identified with this id
 //! seatNumber: this field is used to hold the number of seats a car can have, it has a minimum of 2 seats and a max of 50

@Entity
@Table(name="car")
public class Car {
	
	@Id
	@SequenceGenerator(name="CI_SEQ", sequenceName="car_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CI_SEQ")
	@Column(name="car_id")
	private UUID carID;
	
	@Column(name="seats")
	@NotEmpty
	@Positive
	@Min(2)
	@Max(50)
	private int seatNumber;

	public Car() {
		super();
		
	}

	public Car(UUID carID, @NotEmpty @Positive @Min(2) @Max(50) int seatNumber) {
		super();
		this.carID = carID;
		this.seatNumber = seatNumber;
	}

	public UUID getCarID() {
		return carID;
	}

	public void setCarID(UUID carID) {
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
		result = prime * result + ((carID == null) ? 0 : carID.hashCode());
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
		if (carID == null) {
			if (other.carID != null)
				return false;
		} else if (!carID.equals(other.carID))
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
