package com.revature.bean;

/**
 * LocationID - the ID for a location in the database that provides where an associate lives.
 * Address1 - The provided address a associate gives. Address2 - An additional address the associate
 * may optionally provide. City - The city the associate lives in. State - the state the associate
 * lives in. ZipCode - the zipcode for the area the associate lives in. HousingLocationName - The
 * name of the housing location. Example: IQ Apartments. TrainingLocation - The location of where
 * the training for the associates takes place.
 */
public class HouseLocation {
  private int locationID;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zipCode;
  private String housingLocationName;
  private TrainingLocation trainingLocation;

  public int getlocationID() {
    return locationID;
  }

  public void setlocationID(int locationID) {
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

  /**
   * Housing location DTO constructor that takes in all elements.
   * 
   * @param locationID ID representing a particular housing location DTO.
   * @param address1 The street address.
   * @param address2 The apartment number.
   * @param city The city.
   * @param state The state.
   * @param zipCode The zip code.
   * @param housingLocationName The name of the housing location.
   * @param trainingLocation The training location DTO object.
   */
  public HouseLocation(int locationID, String address1, String address2, String city, String state,
      String zipCode, String housingLocationName, TrainingLocation trainingLocation) {
    super();
    this.locationID = locationID;
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.housingLocationName = housingLocationName;
    this.trainingLocation = trainingLocation;
  }

  @Override
  public String toString() {
    return "HouseLocation [locationID=" + locationID + ", address1=" + address1 + ", address2="
        + address2 + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
        + ", housingLocationName=" + housingLocationName + ", trainingLocation=" + trainingLocation
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
    result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + locationID;
    result = prime * result + ((housingLocationName == null) ? 0 : housingLocationName.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((trainingLocation == null) ? 0 : trainingLocation.hashCode());
    result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    HouseLocation other = (HouseLocation) obj;
    if (address1 == null) {
      if (other.address1 != null) {
        return false;
      }
    } else if (!address1.equals(other.address1)) {
      return false;
    }
    if (address2 == null) {
      if (other.address2 != null) {
        return false;
      }
    } else if (!address2.equals(other.address2)) {
      return false;
    }
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!city.equals(other.city)) {
      return false;
    }
    if (locationID != other.locationID) {
      return false;
    }
    if (housingLocationName == null) {
      if (other.housingLocationName != null) {
        return false;
      }
    } else if (!housingLocationName.equals(other.housingLocationName)) {
      return false;
    }
    if (state == null) {
      if (other.state != null) {
        return false;
      }
    } else if (!state.equals(other.state)) {
      return false;
    }
    if (trainingLocation == null) {
      if (other.trainingLocation != null) {
        return false;
      }
    } else if (!trainingLocation.equals(other.trainingLocation)) {
      return false;
    }
    if (zipCode == null) {
      if (other.zipCode != null) {
        return false;
      }
    } else if (!zipCode.equals(other.zipCode)) {
      return false;
    }
    return true;
  }

  public HouseLocation() {
    super();
  }

}
