package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.bean.Car;
import com.revature.bean.User;
import com.revature.repo.UserRepo;
import com.revature.service.UserServiceImpl;

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

  @InjectMocks
  private UserServiceImpl userServiceImpl = new UserServiceImpl();

  private Car car;

  private User newUser;

  private User existingUser;

  private User nullUser;

  private User badFormatUser;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {

    existingUser = new User(1, "Existing User", null, null, null, null, null, false, 0, car);
    newUser = new User(1, "New User", null, null, null, null, null, false, 0, car);
    nullUser = null;
    badFormatUser = new User(1, "", null, null, null, null, null, false, 0, car);
    car = new Car(1, 4);

  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testCreateNewUser() {
    when(userRepo.save(newUser)).thenReturn(newUser);
    assertEquals(newUser, userServiceImpl.createUser(newUser));
    verify(userRepo).save(newUser);
  }

  @Test
  void testCreateExistingUser() {
    when(userRepo.save(existingUser)).thenReturn(existingUser);
    Assertions.assertThrows(DuplicateKeyException.class, () -> {
      userServiceImpl.createUser(existingUser);
    });

  }

  @Test
  void testCreateNullUser() {
    when(userRepo.save(nullUser)).thenThrow(IllegalArgumentException.class);
    Assertions.assertThrows(NullPointerException.class, () -> {
      userServiceImpl.createUser(nullUser);
    });
  }

  @Test
  void testCreateBadFormatUser() {
    when(userRepo.save(badFormatUser)).thenThrow(ConstraintViolationException.class);
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      userServiceImpl.createUser(badFormatUser);
    });
  }
}
