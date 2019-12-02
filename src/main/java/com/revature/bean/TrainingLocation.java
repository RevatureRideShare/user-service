package com.revature.bean;

import org.springframework.stereotype.Component;

@Component
public class TrainingLocation {
  private int trainingLocationID;
  private String trainingLocationName;


  /**
   * Training location DTO constructor.
   * 
   * @param trainingLocationID ID in the database that matches a particular training location.
   * @param trainingLocationName Name in the database that matches a particular training location.
   */
  public TrainingLocation(int trainingLocationID, String trainingLocationName) {
    super();
    this.trainingLocationID = trainingLocationID;
    this.trainingLocationName = trainingLocationName;
  }

  @Override
  public String toString() {
    return "TrainingLocationDto [trainingLocationID=" + trainingLocationID
        + ", trainingLocationName=" + trainingLocationName + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + trainingLocationID;
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
    TrainingLocation other = (TrainingLocation) obj;
    if (trainingLocationID != other.trainingLocationID) {
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

  public int getTrainingLocationID() {
    return trainingLocationID;
  }

  public void setTrainingLocationID(int trainingLocationID) {
    this.trainingLocationID = trainingLocationID;
  }

  public String getTrainingLocationName() {
    return trainingLocationName;
  }

  public void setTrainingLocationName(String trainingLocationName) {
    this.trainingLocationName = trainingLocationName;
  }

  public TrainingLocation() {
    super();
  }

}
