package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.bean.Admin;
import com.revature.bean.Car;
import com.revature.bean.HousingLocation;
import com.revature.bean.Security;
import com.revature.bean.TrainingLocation;
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
class TrainingLocationServiceImplTest {

  @Mock
  private UserRepo userRepo;

  @InjectMocks
  private UserServiceImpl userServiceImpl = new UserServiceImpl();

  private Admin admin;

  private Car car;

  private HousingLocation housingLocation;

  private Security security;

  private TrainingLocation trainingLocation;

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

  // @Test
  // void testFindByIdNewTrainingLocation() {
  // when(trainingLocationRepo.findById(newTrainingLocation.getTrainingLocationID()))
  // .thenReturn(Optional.empty());
  // assertEquals(Optional.empty(), trainingLocationServiceImpl
  // .getTrainingLocation(newTrainingLocation.getTrainingLocationID()));
  //
  // }
  //
  // @Test
  // void testFindByIdExistingTrainingLocation() {
  // when(trainingLocationRepo.findById(existingTrainingLocation.getTrainingLocationID()))
  // .thenReturn(Optional.of(existingTrainingLocation));
  // assertEquals(Optional.of(existingTrainingLocation), trainingLocationServiceImpl
  // .getTrainingLocation(existingTrainingLocation.getTrainingLocationID()));
  // }
  //
  // @Test
  // void testFindByIdNullTrainingLocation() {
  // when(trainingLocationRepo.findById(null)).thenThrow(NullPointerException.class);
  // Assertions.assertThrows(NullPointerException.class, () -> {
  // trainingLocationServiceImpl.getTrainingLocation(nullTrainingLocation.getTrainingLocationID());
  // });
  // }
  //
  // @Test
  // void testFindByIdBadFormatTrainingLocation() {
  // when(trainingLocationRepo.findById(badFormatTrainingLocation.getTrainingLocationID()))
  // .thenReturn(Optional.empty());
  // assertEquals(Optional.empty(), trainingLocationServiceImpl
  // .getTrainingLocation(badFormatTrainingLocation.getTrainingLocationID()));
  // }
  //
  // @Test
  // void testTrainingLocationList() {
  // List<TrainingLocation> existingTLocationList = new ArrayList<>();
  // existingTLocationList.add(existingTrainingLocation);
  // when(trainingLocationRepo.findAll()).thenReturn(existingTLocationList);
  // assertEquals(existingTLocationList, trainingLocationServiceImpl.getAllTrainingLocations());
  // }

}
