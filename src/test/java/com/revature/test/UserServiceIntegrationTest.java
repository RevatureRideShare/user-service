package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.revature.bean.User;
import com.revature.exception.UpdateNonexistentException;
import com.revature.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class UserServiceIntegrationTest {

  private UserServiceImpl userServiceImpl;
  private User existingUser;
  private User updatedUser;
  private User nullUser;
  private User nonExistingUser;
  private User changedUser;
  private User newUser;

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
    existingUser = new User(26, "bmoney@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    updatedUser = new User(27, "bmoney2@gmail.com", "NotChanged", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    nonExistingUser = new User(999, "IDontExist@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    changedUser = new User(27, "bmoney2@gmail.com", "Changed", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    newUser = new User(2, "NewUser@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  @Sql("user-script.sql")
  void testGetExistingUserByEmail() {
    assertEquals(userServiceImpl.getUserByEmail(existingUser.getEmail()),
        Optional.of(existingUser));
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

  // @Test
  // void testCreateBadFormatUser() {
  // User badFormatUser = new User(5, "", null, null, null, null, null, false, 0);
  // assertThrows(TransactionSystemException.class, () -> {
  // userServiceImpl.createUser(badFormatUser);
  // });

  // }

  @Test
  @Sql("user-script.sql")
  void testCreateNewUser() {
    User extraNewUser = userServiceImpl.createUser(newUser);
    assertEquals(Optional.of(extraNewUser),
        userServiceImpl.getUserByEmail(extraNewUser.getEmail()));
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
    assertEquals(userServiceImpl.getUserByEmail(changedUser.getEmail()), Optional.of(changedUser));
  }

  @Test
  void testUpdateNonExistingUser() {
    assertThrows(UpdateNonexistentException.class, () -> {
      userServiceImpl.updateUser(nonExistingUser);
    });
  }
}
