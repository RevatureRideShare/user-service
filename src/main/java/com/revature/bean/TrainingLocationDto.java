package com.revature.bean;

import org.springframework.stereotype.Component;

@Component
public class TrainingLocationDto {
  private int trainingLocationId;
  private String trainingLocationName;


  /**
   * Training location DTO constructor.
   * 
   * @param trainingLocationId ID in the database that matches a particular training location.
   * @param trainingLocationName Name in the database that matches a particular training location.
   */
  public TrainingLocationDto(int trainingLocationId, String trainingLocationName) {
    super();
    this.trainingLocationId = trainingLocationId;
    this.trainingLocationName = trainingLocationName;
  }

  @Override
  public String toString() {
    return "TrainingLocationDto [trainingLocationId=" + trainingLocationId
        + ", trainingLocationName=" + trainingLocationName + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + trainingLocationId;
    result =
        prime * result + ((trainingLocationName == null) ? 0 : trainingLocationName.hashCode());
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
    TrainingLocationDto other = (TrainingLocationDto) obj;
    if (trainingLocationId != other.trainingLocationId) {
      return false;
    }
    if (trainingLocationName == null) {
      if (other.trainingLocationName != null) {
        return false;
      }
    } else if (!trainingLocationName.equals(other.trainingLocationName)) {
      return false;
    }
    return true;
  }

  public int getTrainingLocationId() {
    return trainingLocationId;
  }

  public void setTrainingLocationId(int trainingLocationId) {
    this.trainingLocationId = trainingLocationId;
  }

  public String getTrainingLocationName() {
    return trainingLocationName;
  }

  public void setTrainingLocationName(String trainingLocationName) {
    this.trainingLocationName = trainingLocationName;
  }

  public TrainingLocationDto() {
    super();
  }

}
