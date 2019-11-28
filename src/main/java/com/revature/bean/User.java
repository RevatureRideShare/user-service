package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * This objects purpose is to hold a users information. It's fields consist of: userID: a user id
 * used to identify a user, can be used to grab user objects; email: an email used for credentials
 * validation; firstName: a field to hold a user's first name; lastName: a field to hold a user's
 * last name; phoneNumber: a field to hold the users phone number for contact purposes; rideStatus:
 * an enum to determine if the user is active or inactive in terms of giving rides; role: an enum to
 * determine whether the user is a rider or driver; accountStatus: a boolean value to determine
 * whether the users account is activated or deactivated; locationID: an id for location to
 * determine where the user lives, this id is references in another service; car: a car object that
 * can be tied to a user (if they're a driver) that lets us identify whether they have a car or not.
 * 
 * @author: Jane Shin
 * @author: Roberto Rodriguez
 * @author: Erik Haklar
 */
@Entity
@Table(name = "users")
public class User {

  public enum RideStatus {
    INACTIVE, ACTIVE
  }

  public enum Role {
    RIDER, DRIVER
  }


  @Id
  @SequenceGenerator(name = "UI_SEQ", sequenceName = "user_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UI_SEQ")
  @Column(name = "user_id")
  private int userID;

  @Column(name = "email")
  @Email
  @NotEmpty
  private String email;

  @Column(name = "first_name")
  @NotEmpty
  @Size(max = 50)
  private String firstName;

  @Column(name = "last_name")
  @NotEmpty
  @Size(max = 50)
  private String lastName;

  @Column(name = "phone_number")
  @NotEmpty
  @Size(max = 20)
  @Pattern(regexp = "^[0-9-]*$")
  private String phoneNumber;

  @Column(name = "ride_status")
  //@NotEmpty
  //@Size(max = 25)
  RideStatus rideStatus;

  @Column(name = "role")
  //@NotEmpty
  //@Size(max = 25)
  Role role;

  @Column(name = "account_status")
  private boolean accountStatus;

  @Column(name = "location_id")
  //@NotEmpty
  int locationID;

  // @JoinColumn(name = "car_id")
  // @OneToOne
  // Car car;

  public User() {
    super();

  }

  /**
   * This User constructor users the below parameters.
   * 
   * @param userID The user id which the user can be grabbed by
   * @param email The email address associated with the user
   * @param firstName The first name of the user
   * @param lastName The last name of the user
   * @param phoneNumber The phone number associated with the user
   * @param rideStatus The status of the user depending on whether they are giving/accepting rides
   *        or not
   * @param role The role of the user
   * @param accountStatus The status of the user depending on whether their account is activated or
   *        deactivated
   * @param locationID The location id of the housing location associated with the user
   * @param car The car object tied to the user
   */
  public User(int userID, @Email @NotEmpty String email, @NotEmpty @Size(max = 50) String firstName,
      @NotEmpty @Size(max = 50) String lastName,
      @NotEmpty @Size(max = 20) @Pattern(regexp = "^[0-9-]*$") String phoneNumber,
      @NotEmpty @Size(max = 25) String rideStatus, @NotEmpty @Size(max = 25) String role,
      boolean accountStatus, int locationID) {
    super();
    this.userID = userID;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.rideStatus = RideStatus.valueOf(rideStatus);
    this.role = Role.valueOf(role);
    this.accountStatus = accountStatus;
    this.locationID = locationID;
    // this.car = car;
  }

  public User(int userID, @Email @NotEmpty String email, @NotEmpty @Size(max = 50) String firstName,
      @NotEmpty @Size(max = 50) String lastName,
      @NotEmpty @Size(max = 20) @Pattern(regexp = "^[0-9-]*$") String phoneNumber,
      RideStatus rideStatus, Role role, boolean accountStatus, int locationID) {
    super();
    this.userID = userID;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.rideStatus = rideStatus;
    this.role = role;
    this.accountStatus = accountStatus;
    this.locationID = locationID;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

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

  public RideStatus getRideStatus() {
    return rideStatus;
  }

  public void setRideStatus(RideStatus rideStatus) {
    this.rideStatus = rideStatus;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public boolean isAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(boolean accountStatus) {
    this.accountStatus = accountStatus;
  }

  public int getLocationID() {
    return locationID;
  }

  public void setLocationID(int locationID) {
    this.locationID = locationID;
  }

  /*
   * public Car getCar() { return car; }
   */

  /*
   * public void setCar(Car car) { this.car = car; }
   */

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (accountStatus ? 1231 : 1237);
    // result = prime * result + ((car == null) ? 0 : car.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + locationID;
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((rideStatus == null) ? 0 : rideStatus.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    result = prime * result + userID;
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
    User other = (User) obj;
    if (accountStatus != other.accountStatus)
      return false;
    /*
     * if (car == null) { if (other.car != null) return false; } else if (!car.equals(other.car))
     * return false;
     */
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (locationID != other.locationID)
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    if (rideStatus != other.rideStatus)
      return false;
    if (role != other.role)
      return false;
    if (userID != other.userID)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [userID=" + userID + ", email=" + email + ", firstName=" + firstName
        + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", rideStatus=" + rideStatus
        + ", role=" + role + ", accountStatus=" + accountStatus + ", locationID=" + locationID
        + /* ", car=" + car + */"]";
  }



}
