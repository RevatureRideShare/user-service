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
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin")
public class Admin {

  @Id
  @SequenceGenerator(name = "A_SEQ", sequenceName = "admin_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "A_SEQ")
  @Column(name = "admin_id")
  int adminID;

  @Column(name = "email")
  @Email
  @NotEmpty
  String email;

  @Column(name = "first_name")
  @NotEmpty
  @Size(max = 50)
  String firstName;

  @Column(name = "last_name")
  @NotEmpty
  @Size(max = 50)
  String lastName;

  @Column(name = "account_status")
  boolean accountStatus;

  public int getAdminID() {
    return adminID;
  }

  public void setAdminID(int adminID) {
    this.adminID = adminID;
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

  public boolean isAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(boolean accountStatus) {
    this.accountStatus = accountStatus;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (accountStatus ? 1231 : 1237);
    result = prime * result + adminID;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
    Admin other = (Admin) obj;
    if (accountStatus != other.accountStatus)
      return false;
    if (adminID != other.adminID)
      return false;
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
    return true;
  }

  @Override
  public String toString() {
    return "Admin [adminID=" + adminID + ", email=" + email + ", firstName=" + firstName
        + ", lastName=" + lastName + ", accountStatus=" + accountStatus + "]";
  }

}
