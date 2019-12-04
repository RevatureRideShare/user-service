package com.revature.bean;

/**
 * A DTO allows for easy transfer of Angular Typescript data to Java code. This DTO is for grabbing
 * information entered by a user on the front end about that user.
 * 
 * Email - the email the user provided. FirstName - the first name of said user. LastName - the last
 * name of said user. PhoneNumber - the phone number of said user. RideStatus - Does the user
 * currently have a car? AccountStatus - Is this user account still active or banned/dropped?
 * HouseLocation - the Bean for the location of where the user lives. CarDTO - The Bean for the
 * information of the user's car.
 */
public class UserDto {
  private String email;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String rideStatus;
  private String role;
  private boolean accountStatus;
  private HouseLocation houseLocation;
  private CarDto carDto;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getRideStatus() {
    return rideStatus;
  }

  public void setRideStatus(String rideStatus) {
    this.rideStatus = rideStatus;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean isAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(boolean accountStatus) {
    this.accountStatus = accountStatus;
  }

  public HouseLocation getHouseLocation() {
    return houseLocation;
  }

  public void setHouseLocation(HouseLocation houseLocation) {
    this.houseLocation = houseLocation;
  }

  public CarDto getCarDto() {
    return carDto;
  }

  public void setCarDto(CarDto carDto) {
    this.carDto = carDto;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (accountStatus ? 1231 : 1237);
    result = prime * result + ((carDto == null) ? 0 : carDto.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((houseLocation == null) ? 0 : houseLocation.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((rideStatus == null) ? 0 : rideStatus.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
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
    UserDto other = (UserDto) obj;
    if (accountStatus != other.accountStatus) {
      return false;
    }
    if (carDto == null) {
      if (other.carDto != null) {
        return false;
      }
    } else if (!carDto.equals(other.carDto)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (firstName == null) {
      if (other.firstName != null) {
        return false;
      }
    } else if (!firstName.equals(other.firstName)) {
      return false;
    }
    if (houseLocation == null) {
      if (other.houseLocation != null) {
        return false;
      }
    } else if (!houseLocation.equals(other.houseLocation)) {
      return false;
    }
    if (lastName == null) {
      if (other.lastName != null) {
        return false;
      }
    } else if (!lastName.equals(other.lastName)) {
      return false;
    }
    if (phoneNumber == null) {
      if (other.phoneNumber != null) {
        return false;
      }
    } else if (!phoneNumber.equals(other.phoneNumber)) {
      return false;
    }
    if (rideStatus == null) {
      if (other.rideStatus != null) {
        return false;
      }
    } else if (!rideStatus.equals(other.rideStatus)) {
      return false;
    }
    if (role == null) {
      if (other.role != null) {
        return false;
      }
    } else if (!role.equals(other.role)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "UserDto [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
        + ", phoneNumber=" + phoneNumber + ", rideStatus=" + rideStatus + ", role=" + role
        + ", accountStatus=" + accountStatus + ", houseLocation=" + houseLocation + ", carDto="
        + carDto + "]";
  }

  /**
   * UserDto is there to help transfer User objects from Angular into Java.
   * 
   * @param email User's email.
   * @param firstName User's first name.
   * @param lastName User's last name.
   * @param phoneNumber User's phone number.
   * @param rideStatus User's ride status.
   * @param role User's role.
   * @param accountStatus User's account status.
   * @param houseLocation User's housing location (Housing object nested inside due to angular).
   * @param carDto User's car (Car object nested inside due to angular).
   */

  public UserDto(String email, String firstName, String lastName, String phoneNumber,
      String rideStatus, String role, boolean accountStatus, HouseLocation houseLocation,
      CarDto carDto) {
    super();
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.rideStatus = rideStatus;
    this.role = role;
    this.accountStatus = accountStatus;
    this.houseLocation = houseLocation;
    this.carDto = carDto;
  }

  public UserDto() {
    super();
  }

}
