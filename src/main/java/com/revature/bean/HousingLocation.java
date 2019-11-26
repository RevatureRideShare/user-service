package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "housing_location")
public class HousingLocation {

  @Id
  @SequenceGenerator(name = "L_SEQ", sequenceName = "location_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "L_SEQ")
  @Column(name = "location_id")
  int locationID;

  @Column(name = "address_1")
  String address1;

  @Column(name = "address_2")
  String address2;

  @Column(name = "city")
  String city;

  @Column(name = "state")
  String state;

  @Column(name = "zip_code")
  String zipCode;

  @Column(name = "housing_location_name")
  String housingLocationName;

  @Column(name = "housing_location_id")
  TrainingLocation trainingLocation;

  public int getLocationID() {
    return locationID;
  }

  public void setLocationID(int locationID) {
    this.locationID = locationID;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getHousingLocationName() {
    return housingLocationName;
  }

  public void setHousingLocationName(String housingLocationName) {
    this.housingLocationName = housingLocationName;
  }

  public TrainingLocation getTrainingLocation() {
    return trainingLocation;
  }

  public void setTrainingLocation(TrainingLocation trainingLocation) {
    this.trainingLocation = trainingLocation;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
    result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((housingLocationName == null) ? 0 : housingLocationName.hashCode());
    result = prime * result + locationID;
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((trainingLocation == null) ? 0 : trainingLocation.hashCode());
    result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
    HousingLocation other = (HousingLocation) obj;
    if (address1 == null) {
      if (other.address1 != null)
        return false;
    } else if (!address1.equals(other.address1))
      return false;
    if (address2 == null) {
      if (other.address2 != null)
        return false;
    } else if (!address2.equals(other.address2))
      return false;
    if (city == null) {
      if (other.city != null)
        return false;
    } else if (!city.equals(other.city))
      return false;
    if (housingLocationName == null) {
      if (other.housingLocationName != null)
        return false;
    } else if (!housingLocationName.equals(other.housingLocationName))
      return false;
    if (locationID != other.locationID)
      return false;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    if (trainingLocation == null) {
      if (other.trainingLocation != null)
        return false;
    } else if (!trainingLocation.equals(other.trainingLocation))
      return false;
    if (zipCode == null) {
      if (other.zipCode != null)
        return false;
    } else if (!zipCode.equals(other.zipCode))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "HousingLocation [locationID=" + locationID + ", address1=" + address1 + ", address2="
        + address2 + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
        + ", housingLocationName=" + housingLocationName + ", trainingLocation=" + trainingLocation
        + "]";
  }



}
