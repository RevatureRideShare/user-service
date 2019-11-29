package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.bean.Car;
import com.revature.bean.User;
import com.revature.exception.UpdateNonexistentException;
import com.revature.repo.UserRepo;
import com.revature.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@SpringBootTest
class UserServiceApplicationTest {

  @Mock
  private UserRepo userRepo;

  @Mock
  private User user;

  @InjectMocks
  private UserServiceImpl userServiceImpl = new UserServiceImpl();

  private Car car;

  private User newUser;

  private User existingUser;

  private User nullUser;

  private User nullEmailUser;

  private User emptyStringEmailUser;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {

    existingUser = new User(26, "bmoney@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    newUser =
        new User(60, "newUser@gmail.com", "New", "User", "3309842776", "ACTIVE", "RIDER", false, 0);
    nullUser = null;
    nullEmailUser = new User(80, null, "Null", "Email", "3309842776", "ACTIVE", "RIDER", false, 0);
    emptyStringEmailUser =
        new User(60, "", "Empty", "Email", "3309842776", "ACTIVE", "RIDER", false, 0);
    car = new Car(1, 4, 4);

  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  // Test that this method will call .save and create a user
  void testCreateNewUser() {
    when(userRepo.save(newUser)).thenReturn(newUser);
    assertEquals(newUser, userServiceImpl.createUser(newUser));
    verify(userRepo).save(newUser);
  }

  @Test
  // Test that this method will throw custom exception when creating user with existing email
  void testCreateExistingUser() {
    when(userRepo.findByEmail(existingUser.getEmail())).thenReturn(existingUser);
    Assertions.assertThrows(DuplicateKeyException.class, () -> {
      userServiceImpl.createUser(existingUser);
    });
  }

  @Test
  // Test that this method will throw a custom null pointer exception when trying to create a null
  // object
  void testCreateNullUser() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      userServiceImpl.createUser(nullUser);
    });
  }

  @Test
  // Test that this method will throw a custom null pointer exception when trying to create a user
  // with a null email
  void testCreateUserWithNullEmail() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      userServiceImpl.createUser(nullEmailUser);
    });
  }

  @Test
  // Test that this method will throw a custom null pointer exception when trying to create a user
  // with an empty string email
  void testCreateUserWithEmptyStringEmail() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      userServiceImpl.createUser(emptyStringEmailUser);
    });
  }

  @Test
  // Test that this method will return the correct user and search for the correct email
  void testGetNewUser() {
    when(userRepo.findByEmail(newUser.getEmail())).thenReturn(newUser);
    assertEquals(newUser, userServiceImpl.getUserByEmail(newUser.getEmail()));
    verify(userRepo).findByEmail("newUser@gmail.com");
  }

  @Test
  // Test that this method will throw custom null pointer exception when getting a null object
  void testGetNullUser() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      userServiceImpl.getUserByEmail(nullUser.getEmail());
    });
  }

  @Test
  // Test that this method will will return all users
  void testGetAllUsers() {
    List<User> existingUserList = new ArrayList<>();
    existingUserList.add(existingUser);
    when(userRepo.findAll()).thenReturn(existingUserList);
    assertEquals(existingUserList, userServiceImpl.getAllUsers());
  }

  @Test
  // Test that this method will throw a custom exception thrown when trying to update a user that
  // does not exist
  void testUpdateNewUser() {
    Assertions.assertThrows(UpdateNonexistentException.class, () -> {
      userServiceImpl.updateUser(nullEmailUser);
    });
  }

  @Test
  // Test that this method will successfully update a user that already exists
  void testUpdateExistingUser() {
    User updatedUser = new User(0, existingUser.getEmail(), "Updated Existing User", null, null,
        "ACTIVE", "RIDER", false, 0);
    when(userRepo.findByEmail(updatedUser.getEmail())).thenReturn(updatedUser);
    when(userRepo.save(updatedUser)).thenReturn(updatedUser);
    assertEquals(updatedUser, userServiceImpl.updateUser(updatedUser));
    verify(userRepo).save(updatedUser);
  }

  @Test
  // Test that this method will throw a custom null pointer exception when trying to update a user
  // that is null
  void testUpdateNullUser() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      userServiceImpl.updateUser(nullUser);
    });
  }

  @Test
  // Test that this method will throw a constraint violation exception
  void testUpdateEmptyStringUser() {
    User updatedEmptyStringUser = new User(0, existingUser.getEmail(), "", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    when(userRepo.findByEmail(updatedEmptyStringUser.getEmail()))
        .thenReturn(updatedEmptyStringUser);
    when(userRepo.save(updatedEmptyStringUser)).thenThrow(ConstraintViolationException.class);
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      userServiceImpl.updateUser(updatedEmptyStringUser);
    });
  }
}

