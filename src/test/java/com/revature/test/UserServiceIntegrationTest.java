package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.revature.bean.User;
import com.revature.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;

@SpringBootTest
class UserServiceIntegrationTest {

  private UserServiceImpl userServiceImpl;
  private User existingUser;
  private User updatedUser;
  private User nullUser;
  private User nonExistingUser;
  private User changedUser;
  private User newUser;
  private User badFormatEmptyEmail;
  private User badFormatEmptyFirstName;
  private User badFormatEmptyLastName;
  private User badFormatEmailMissingAtSymbol;
  private User badFormatLargeFirstName;
  private User badFormatLargeLastName;
  private User badFormatPhoneNumber;
  private User badFormatEmptyPhoneNumber;
  private User badFormatLargePhoneNumber;

  @Autowired
  public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
    this.userServiceImpl = userServiceImpl;
  }

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    nullUser = null;
    existingUser = new User(1, "bmoney@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    updatedUser = new User(2, "bmoney2@gmail.com", "NotChanged", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    nonExistingUser = new User(999, "IDontExist@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    changedUser = new User(2, "bmoney2@gmail.com", "Changed", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    newUser = new User(4, "NewUser@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    badFormatEmptyEmail = new User(5, "", "Bad", "Format", "3309842776", User.RideStatus.ACTIVE,
        User.Role.RIDER, true, 0);
    badFormatEmailMissingAtSymbol = new User(6, "bf", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    badFormatEmptyFirstName = new User(7, "bf1@gmail.com", "", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    badFormatLargeFirstName =
        new User(8, "bf2@gmail.com", "Briannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn",
            "Money", "3309842776", User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    badFormatEmptyLastName = new User(9, "bf3@gmail.com", "Brian", "", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    badFormatLargeLastName = new User(10, "bf4@gmail.com", "Brian",
        "Moneyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    badFormatEmptyPhoneNumber = new User(11, "bf5@gmail.com", "Brian", "Money", "",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    badFormatLargePhoneNumber = new User(12, "bf6@gmail.com", "Brian", "Money",
        "3333333333333333333333333333333333333333333333333333333333333333", User.RideStatus.ACTIVE,
        User.Role.RIDER, true, 0);
    badFormatPhoneNumber = new User(13, "bf7@gmail.com", "Brian", "Money", "CallMeMaybe?",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  @Sql("user-script.sql")
  void testGetExistingUserByEmail() {
    assertEquals(userServiceImpl.getUserByEmail(existingUser.getEmail()), existingUser);
  }

  @Test
  @Sql("user-script.sql")
  void testGetAllUsers() {
    List<User> existingUserList = new ArrayList<>();
    existingUserList.add(existingUser);
    existingUserList.add(updatedUser);
    assertEquals(userServiceImpl.getAllUsers(), existingUserList);

    System.out.println(userServiceImpl.getAllUsers());
  }

  @Test
  void testCreatebadFormatEmptyEmailUser() {
    // User badFormatUser = new User(5, "", null, null, null, null, null, false, 0);
    assertThrows(NullPointerException.class, () -> {
      userServiceImpl.createUser(badFormatEmptyEmail);
    });
  }

  @Test
  void testCreateBadFormatEmailMissingAtSymbolUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.createUser(badFormatEmailMissingAtSymbol);
    });
  }

  @Test
  void testCreateBadFormatEmptyFirstNameUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.createUser(badFormatEmptyFirstName);
    });
  }

  @Test
  void testCreateBadFormatLargeFirstNameUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.createUser(badFormatLargeFirstName);
    });
  }

  @Test
  void testCreateBadFormatEmptyLastNameUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.createUser(badFormatEmptyLastName);
    });
  }

  @Test
  void testCreateBadFormatLargeLastNameUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.createUser(badFormatLargeLastName);
    });
  }

  @Test
  void testCreateBadFormatEmptyPhoneNumberUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.createUser(badFormatEmptyPhoneNumber);
    });
  }

  @Test
  void testCreateBadFormatLargePhoneNumberUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.createUser(badFormatLargePhoneNumber);
    });
  }

  @Test
  void testCreateBadFormatPhoneNumberUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.createUser(badFormatPhoneNumber);
    });
  }

  @Test
  @Sql("user-script.sql")
  void testCreateNewUser() {
    User extraNewUser = userServiceImpl.createUser(newUser);
    assertEquals(extraNewUser, userServiceImpl.getUserByEmail(extraNewUser.getEmail()));
  }

  @Test
  @Sql("training-location-script.sql")
  void testCreateExistingUser() {
    assertThrows(DuplicateKeyException.class, () -> {
      userServiceImpl.createUser(existingUser);
    });
  }

  @Test
  void testCreateNullUser() {
    assertThrows(NullPointerException.class, () -> {
      userServiceImpl.createUser(nullUser);
    });
  }

  @Test
  @Sql("user-script.sql")
  void testUpdateExistingUser() {
    System.out.println(
        "Current state of updatedUser" + userServiceImpl.getUserByEmail(updatedUser.getEmail()));
    userServiceImpl.updateUser(changedUser);
    assertEquals(userServiceImpl.getUserByEmail(changedUser.getEmail()), changedUser);
  }

  @Test
  void testUpdateNonExistingUser() {
    assertThrows(TransactionSystemException.class, () -> {
      userServiceImpl.updateUser(nonExistingUser);
    });
  }
}
