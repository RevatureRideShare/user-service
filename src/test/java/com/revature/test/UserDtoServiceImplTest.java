package com.revature.test;

import static org.junit.Assert.assertEquals;

import com.revature.bean.Car;
import com.revature.bean.CarDto;
import com.revature.bean.HouseLocation;
import com.revature.bean.TrainingLocation;
import com.revature.bean.User;
import com.revature.bean.UserDto;
import com.revature.service.UserDtoServiceImpl;
import com.revature.service.UserService;

import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ws.transport.http.HttpUrlConnection;

@SpringBootTest
class UserDtoServiceImplTest {

  @Mock
  private UserService userService;

  @Mock
  HttpUrlConnection connection;

  @InjectMocks
  private UserDtoServiceImpl userDtoServiceImpl = new UserDtoServiceImpl();

  private URL url;

  private User user;

  private UserDto userDto;

  private TrainingLocation trainingLocation;

  private HouseLocation houseLocation;

  private Car car;

  private CarDto carDto;


  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    user = new User(0, "bmoney@gmail.com", "Brian", "Money", "1111111111", User.RideStatus.ACTIVE,
        User.Role.DRIVER, true, 0);
    trainingLocation = new TrainingLocation(0, "USF");
    houseLocation = new HouseLocation(0, "12702 Bruce B Downs Blvd", null, "Tampa", "Florida",
        "33612", "IQ Apartments", trainingLocation);
    car = new Car(0, 0, 5);
    carDto = new CarDto(5);
    userDto = new UserDto("bmoney@gmail.com", "Brian", "Money", "1111111111", "ACTIVE", "DRIVER",
        true, houseLocation, carDto);
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testTranslateDtoInput() {
    assertEquals(user, userDtoServiceImpl.translateDtoInput(userDto));
  }

  @Test
  void testTranslateDtoOutputWithUserAndCar() {
    assertEquals(userDto, userDtoServiceImpl.translateDtoOutput(user, car));
  }

  @Test
  void testTranslateDtoOutputWithUserAndHouseLocation() {

  }

  @Test
  void testTranslateDtoOutputWithUserAndCarAndHouseLocation() {

  }

  @Test
  void testTranslateDtoOutputList() {

  }

}
