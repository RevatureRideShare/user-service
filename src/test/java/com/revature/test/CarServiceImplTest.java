package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.bean.Car;
import com.revature.bean.User;
import com.revature.repo.CarRepo;
import com.revature.repo.UserRepo;
import com.revature.service.CarServiceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
class CarServiceImplTest {
  @Mock
  private CarRepo carRepo;

  @Mock
  private UserRepo userRepo;

  @InjectMocks
  private CarServiceImpl carServiceImpl = new CarServiceImpl();

  private Car newCar;

  private Car existingCar;

  private Car nullCar;

  private Car badFormatCar;

  private User newUser;

  private User existingUser;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    newCar = new Car(1, 1, 3);
    existingCar = new Car(2, 2, 6);
    nullCar = null;
    badFormatCar = new Car(3, 3, 100);

    newUser = new User(1, "bmoneynew@gmail.com", "B", "Money", "4123212321", User.RideStatus.ACTIVE,
        User.Role.DRIVER, true, 0);

    existingUser = new User(2, "bmoney@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.DRIVER, true, 0);
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testCreateNewCar() {
    when(carRepo.findById(newCar.getCarID())).thenReturn(Optional.empty());
    when(carRepo.save(newCar)).thenReturn(newCar);
    assertEquals(newCar, carServiceImpl.createCar(newCar));
    verify(carRepo).save(newCar);
  }

  @Test
  void testCreateExistingCar() {
    when(carRepo.findById(existingCar.getCarID())).thenReturn(Optional.of(existingCar));
    Assertions.assertThrows(DuplicateKeyException.class, () -> {
      carServiceImpl.createCar(existingCar);
    });
  }

  @Test
  void testCreateNullCar() {
    when(carRepo.save(nullCar)).thenThrow(IllegalArgumentException.class);
    Assertions.assertThrows(NullPointerException.class, () -> {
      carServiceImpl.createCar(nullCar);
    });
  }

  @Test
  void testCreateBadFormatCar() {
    when(carRepo.save(badFormatCar)).thenThrow(ConstraintViolationException.class);
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      carServiceImpl.createCar(badFormatCar);
    });
  }

  @Test
  void testGetCarByNewEmail() {
    when(carRepo.findByUserID(newUser.getUserID())).thenThrow(IllegalArgumentException.class);
    Assertions.assertThrows(NullPointerException.class, () -> {
      carServiceImpl.getCarByEmail(newUser.getEmail());
    });
  }

  @Test
  void testGetCarByExistingEmail() {
    when(userRepo.findByEmail(existingUser.getEmail())).thenReturn(existingUser);
    when(carRepo.findByUserID(existingUser.getUserID())).thenReturn(existingCar);
    assertEquals(existingCar, carServiceImpl.getCarByEmail(existingUser.getEmail()));
    verify(userRepo).findByEmail(existingUser.getEmail());
    verify(carRepo).findByUserID(existingUser.getUserID());
  }

  @Test
  void testGetNewCarByID() {
    when(carRepo.findById(newCar.getCarID())).thenReturn(Optional.empty());
    assertEquals(Optional.empty(), carServiceImpl.getCarByID(newCar.getCarID()));
    verify(carRepo).findById(newCar.getCarID());
  }

  @Test
  void testGetExistingCarByID() {
    when(carRepo.findById(existingCar.getCarID())).thenReturn(Optional.of(existingCar));
    assertEquals(Optional.of(existingCar), carServiceImpl.getCarByID(existingCar.getCarID()));
    verify(carRepo).findById(existingCar.getCarID());
  }

  @Test
  void testGetNullCarByID() {
    when(carRepo.findById(null)).thenThrow(IllegalArgumentException.class);
    Assertions.assertThrows(NullPointerException.class, () -> {
      carServiceImpl.getCarByID(nullCar.getCarID());
    });
  }

  @Test
  void testBadFormatCarGetByID() {
    when(carRepo.findById(badFormatCar.getCarID())).thenReturn(Optional.empty());
    assertEquals(Optional.empty(), carServiceImpl.getCarByID(badFormatCar.getCarID()));
  }

  @Test
  void testGetAllCars() {
    List<Car> existingList = new LinkedList<>();
    existingList.add(existingCar);
    when(carRepo.findAll()).thenReturn(existingList);
    assertEquals(existingList, carServiceImpl.getAllCars());
  }
}