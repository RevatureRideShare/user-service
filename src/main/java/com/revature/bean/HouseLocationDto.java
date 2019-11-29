package com.revature.bean;

public class HouseLocationDto {
  private int housingLocationId;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zipCode;
  private String housingLocationName;
  private TrainingLocationDto trainingLocationDto;

  public int gethousingLocaitonId() {
    return housingLocationId;
  }

  public void sethousingLocaitonId(int housingLocationId) {
    this.housingLocationId = housingLocationId;
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

  public TrainingLocationDto getTrainingLocationDto() {
    return trainingLocationDto;
  }

  public void setTrainingLocationDto(TrainingLocationDto trainingLocationDto) {
    this.trainingLocationDto = trainingLocationDto;
  }

  /**
   * Housing location DTO constructor that takes in all elements.
   * 
   * @param housingLocationId ID representing a particular housing location DTO.
   * @param address1 The street address.
   * @param address2 The apartment number.
   * @param city The city.
   * @param state The state.
   * @param zipCode The zip code.
   * @param housingLocationName The name of the housing location.
   * @param trainingLocationDto The training location DTO object.
   */
  public HouseLocationDto(int housingLocationId, String address1, String address2, String city,
      String state, String zipCode, String housingLocationName,
      TrainingLocationDto trainingLocationDto) {
    super();
    this.housingLocationId = housingLocationId;
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.housingLocationName = housingLocationName;
    this.trainingLocationDto = trainingLocationDto;
  }

  @Override
  public String toString() {
    return "HouseLocationDto [housingLocationId=" + housingLocationId + ", address1=" + address1
        + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
        + ", housingLocationName=" + housingLocationName + ", trainingLocationDto="
        + trainingLocationDto + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
    result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + housingLocationId;
    result = prime * result + ((housingLocationName == null) ? 0 : housingLocationName.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((trainingLocationDto == null) ? 0 : trainingLocationDto.hashCode());
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
    HouseLocationDto other = (HouseLocationDto) obj;
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
    if (housingLocationId != other.housingLocationId) {
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
    if (trainingLocationDto == null) {
      if (other.trainingLocationDto != null) {
        return false;
      }
    } else if (!trainingLocationDto.equals(other.trainingLocationDto)) {
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

  public HouseLocationDto() {
    super();
  }

}
