package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Car;
import com.revature.bean.CarDto;
import com.revature.bean.HouseLocation;
import com.revature.bean.TrainingLocation;
import com.revature.bean.User;
import com.revature.bean.User.Role;
import com.revature.bean.UserDto;
import com.revature.controller.UserControllerImpl;
import com.revature.service.CarService;
import com.revature.service.UserDtoService;
import com.revature.service.UserService;

import java.util.LinkedList;
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
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class UserControllerTest {
  @Mock
  UserService userService;

  @Mock
  CarService carService;

  @Mock
  UserDtoService userDtoService;

  @InjectMocks
  UserControllerImpl userControllerImpl;

  private MockMvc mvc;

  private TrainingLocation trainingLocation;

  private HouseLocation houseLocation;

  private Car car;

  private CarDto carDto;

  private User newUser;

  private UserDto newUserDto;

  private User existingUser;

  private UserDto existingUserDto;

  private UserDto nullUserDto;

  private User badFormatUser;

  private UserDto badFormatUserDto;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    this.mvc = MockMvcBuilders.standaloneSetup(userControllerImpl).build();

    car = new Car(0, 0, 5);
    carDto = new CarDto(5);
    trainingLocation = new TrainingLocation(0, "USF");
    houseLocation = new HouseLocation(0, "12702 Bruce B Downs Blvd", null, "Tampa", "Florida",
        "33612", "IQ Apartments", trainingLocation);

    newUser = new User(4, "NewUser@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    existingUser = new User(0, "bmoney@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    badFormatUser = new User(7, "bf1@gmail.com", "", "Money", "3309842776", User.RideStatus.ACTIVE,
        User.Role.RIDER, true, 0);

    newUserDto = new UserDto("NewUser@gmail.com", "Brian", "Money", "3309842776", "ACTIVE", "RIDER",
        true, houseLocation, carDto);
    existingUserDto = new UserDto("bmoney@gmail.com", "Brian", "Money", "1111111111", "ACTIVE",
        "DRIVER", true, houseLocation, carDto);
    nullUserDto = null;
    badFormatUserDto = new UserDto("bf1@gmail.com", "", "Money", "3309842776", "ACTIVE", "RIDER",
        true, houseLocation, carDto);
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testCreateNewUser() throws JsonProcessingException, Exception {
    when(userDtoService.translateDtoInput(newUserDto)).thenReturn(newUser);
    when(userService.createUser(newUser)).thenReturn(newUser);
    when(carService.createCar(car)).thenReturn(car);
    when(userDtoService.translateDtoOutput(newUser, car, newUserDto.getHouseLocation()))
        .thenReturn(newUserDto);

    mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(newUserDto))
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
  }

  @Test
  void testCreateExistingUser() throws JsonProcessingException, Exception {
    when(userDtoService.translateDtoInput(existingUserDto)).thenReturn(existingUser);
    when(userService.createUser(existingUser)).thenThrow(DuplicateKeyException.class);

    Assertions.assertThrows(DuplicateKeyException.class, () -> {
      userControllerImpl.createUser(existingUserDto);
    });
  }

  @Test
  void testCreateNullUser() throws JsonProcessingException, Exception {
    when(userDtoService.translateDtoInput(nullUserDto)).thenThrow(NullPointerException.class);

    Assertions.assertThrows(NullPointerException.class, () -> {
      userControllerImpl.createUser(nullUserDto);
    });
  }

  @Test
  void testCreateBadFormatUser() throws JsonProcessingException, Exception {
    when(userDtoService.translateDtoInput(badFormatUserDto)).thenReturn(badFormatUser);
    when(userService.createUser(badFormatUser)).thenThrow(ConstraintViolationException.class);

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      userControllerImpl.createUser(badFormatUserDto);
    });
  }

  @Test
  void testUpdateNewUser() throws Exception {
    when(userDtoService.translateDtoInput(newUserDto)).thenReturn(newUser);
    when(userService.getUserByEmail(newUser.getEmail())).thenReturn(null);

    Assertions.assertThrows(NullPointerException.class, () -> {
      userControllerImpl.updateUser(newUser.getEmail(), newUserDto);
    });
  }

  @Test
  void testUpdateExistingUser() throws Exception {
    when(userDtoService.translateDtoInput(existingUserDto)).thenReturn(existingUser);
    when(userService.getUserByEmail(existingUser.getEmail())).thenReturn(existingUser);
    when(userService.updateUser(existingUser)).thenReturn(existingUser);
    when(carService.getCarByEmail(existingUser.getEmail())).thenReturn(car);
    when(userDtoService.translateDtoOutput(existingUser, car, existingUserDto.getHouseLocation()))
        .thenReturn(existingUserDto);

    assertEquals(new ResponseEntity<>(existingUserDto, HttpStatus.OK),
        userControllerImpl.updateUser(existingUser.getEmail(), existingUserDto));
  }

  @Test
  void testUpdateNullUser() throws Exception {
    when(userDtoService.translateDtoInput(nullUserDto)).thenThrow(NullPointerException.class);

    Assertions.assertThrows(NullPointerException.class, () -> {
      userControllerImpl.updateUser("", nullUserDto);
    });
  }

  @Test
  void testUpdateBadFormatUser() throws Exception {
    when(userDtoService.translateDtoInput(badFormatUserDto)).thenReturn(badFormatUser);
    when(userService.getUserByEmail(badFormatUser.getEmail())).thenReturn(badFormatUser);
    when(userService.updateUser(badFormatUser)).thenThrow(ConstraintViolationException.class);

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      userControllerImpl.updateUser(badFormatUser.getEmail(), badFormatUserDto);
    });
  }

  @Test
  void testActivateOrInactivateNewUser() throws Exception {
    when(userDtoService.translateDtoInput(newUserDto)).thenReturn(newUser);
    when(userService.getUserByEmail(newUser.getEmail())).thenReturn(null);

    Assertions.assertThrows(NullPointerException.class, () -> {
      userControllerImpl.activateOrInactivateUser(newUser.getEmail(), newUserDto);
    });
  }

  @Test
  void testActivateOrInactivateExistingUser() throws Exception {
    when(userDtoService.translateDtoInput(existingUserDto)).thenReturn(existingUser);
    when(userService.getUserByEmail(existingUser.getEmail())).thenReturn(existingUser);
    when(userService.updateUser(existingUser)).thenReturn(existingUser);
    when(carService.getCarByEmail(existingUser.getEmail())).thenReturn(car);
    when(userDtoService.translateDtoOutput(existingUser, car, existingUserDto.getHouseLocation()))
        .thenReturn(existingUserDto);

    assertEquals(new ResponseEntity<>(existingUserDto, HttpStatus.OK),
        userControllerImpl.activateOrInactivateUser(existingUser.getEmail(), existingUserDto));
  }

  @Test
  void testActivateOrInactivateNullUser() throws Exception {
    when(userDtoService.translateDtoInput(nullUserDto)).thenThrow(NullPointerException.class);

    Assertions.assertThrows(NullPointerException.class, () -> {
      userControllerImpl.activateOrInactivateUser("", nullUserDto);
    });
  }

  @Test
  void testActivateOrInactivateBadFormatUser() throws Exception {
    when(userDtoService.translateDtoInput(badFormatUserDto)).thenReturn(badFormatUser);
    when(userService.getUserByEmail(badFormatUser.getEmail())).thenReturn(badFormatUser);
    when(userService.updateUser(badFormatUser)).thenThrow(ConstraintViolationException.class);

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      userControllerImpl.activateOrInactivateUser(badFormatUser.getEmail(), badFormatUserDto);
    });
  }

  @Test
  void testGetAllUsersByRole() throws Exception {
    List<User> existingRiderUserList = new LinkedList<>();
    existingRiderUserList.add(existingUser);
    List<UserDto> existingRiderUserDtoList = new LinkedList<>();
    existingRiderUserDtoList.add(existingUserDto);

    when(userService.getAllUsersByRole(Role.RIDER)).thenReturn(existingRiderUserList);
    when(userDtoService.translateDtoOutput(existingRiderUserList))
        .thenReturn(existingRiderUserDtoList);

    mvc.perform(MockMvcRequestBuilders.get("/user/?role=" + Role.RIDER)).andExpect(status().isOk())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(existingRiderUserDtoList)));
  }

  @Test
  void testGetNewUser() throws Exception {
    when(userService.getUserByEmail(newUser.getEmail())).thenReturn(null);
    when(carService.getCarByEmail(newUser.getEmail())).thenReturn(null);
    Car car = new Car(0, 0, 0);
    when(userDtoService.translateDtoOutput((User) null, car)).thenThrow(NullPointerException.class);

    Assertions.assertThrows(NullPointerException.class, () -> {
      userControllerImpl.getUser(newUser.getEmail());
    });
  }

  @Test
  void testGetExistingUser() throws Exception {
    when(userService.getUserByEmail(existingUser.getEmail())).thenReturn(existingUser);
    when(carService.getCarByEmail(existingUser.getEmail())).thenReturn(car);

    // when(existingUserDto.getHouseLocation()).thenReturn(houseLocation);
    when(userDtoService.translateDtoOutput(existingUser, car)).thenReturn(existingUserDto);
    // when(userDtoService.translateDtoOutput(existingUser, existingUserDto.getHouseLocation()))
    // .thenReturn(existingUserDto);

    assertEquals(new ResponseEntity<>(existingUserDto, HttpStatus.OK),
        userControllerImpl.getUser(existingUser.getEmail()));
  }

  @Test
  void testGetAllUsers() throws Exception {
    List<User> existingUserList = new LinkedList<>();
    existingUserList.add(existingUser);
    List<UserDto> existingUserDtoList = new LinkedList<>();
    existingUserDtoList.add(existingUserDto);

    when(userService.getAllUsers()).thenReturn(existingUserList);
    when(userDtoService.translateDtoOutput(existingUserList)).thenReturn(existingUserDtoList);

    mvc.perform(MockMvcRequestBuilders.get("/user")).andExpect(status().isOk())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(existingUserDtoList)));
  }

}

