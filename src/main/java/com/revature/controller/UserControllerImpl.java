package com.revature.controller;

import static com.revature.util.LoggerUtil.trace;
import com.revature.bean.Car;
import com.revature.bean.User;
import com.revature.bean.User.Role;
import com.revature.bean.UserDto;
import com.revature.service.CarService;
import com.revature.service.UserDtoService;
import com.revature.service.UserService;
import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is for communication with the front-end. Handles all posts and requests for user
 * related management and information.
 */
@RestController
public class UserControllerImpl implements UserController {

  UserService userService;
  CarService carService;
  UserDtoService userDtoService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setCarService(CarService carService) {
    this.carService = carService;
  }

  @Autowired
  public void setUserDtoService(UserDtoService userDtoService) {
    this.userDtoService = userDtoService;
  }

  /**
   * Here, the front-end tells the back-end to create a new user by passing in a userDTO which is
   * used to get the user object for that user.
   */
  @Override
  @PostMapping("/user")
  public ResponseEntity<?> createUser(@RequestBody(required = false) UserDto userDto) {

    // Creates a new user object.
    trace("createUser input:" + userDto);
    User user = userDtoService.translateDtoInput(userDto);
    user = userService.createUser(user);

    // Creates the user's car.
    Car car = new Car(0, user.getUserID(), userDto.getCarDto().getSeatNumber());
    car = carService.createCar(car);

    // Translates any changes back into a userDto object and returns them.
    if (car != null) {
      userDto = userDtoService.translateDtoOutput(user, car, userDto.getHouseLocation());
    } else {
      userDto = userDtoService.translateDtoOutput(user, userDto.getHouseLocation());
    }
    ResponseEntity<?> responseEntity = new ResponseEntity<>(userDto, HttpStatus.CREATED);
    trace("createUser output:" + responseEntity);
    return responseEntity;
  }


  /**
   * Method controller for updating a currently existing user in the database. The method turns a
   * user Data Transfer object from the request body into a User object. It then calls the service
   * method to update that user by persisting any changes made to it in the database. the method
   * needs to return a response entity since we are utilizing DTOs. Logic flow is performed to find
   * out whether the user has a car or not and calls the appropriate overloaded method in the
   * userDtoService class. Then it returns the DTO.
   * ----------------------------------------------------------------------------------------------
   * Here the front-end asks the back-end to update a user to be either active or inactive. The
   * front-end passes in a email allowing for the identification of the user object associated and a
   * userDTO object. This information is used to update the user's information in the back-end.
   * Returns a userDTO object and HttpStatus code. Note: This is for when a user wants to update
   * their own information.
   */
  @Override
  @PutMapping("/user/{email}")
  public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
    trace("updateUser input:" + email + ", " + userDto);
    User user = userDtoService.translateDtoInput(userDto);
    User existingUser = userService.getUserByEmail(user.getEmail());
    user.setUserID(existingUser.getUserID());
    user = userService.updateUser(user);
    Car car = carService.getCarByEmail(user.getEmail());
    if (car != null) {
      userDto = userDtoService.translateDtoOutput(user, car, userDto.getHouseLocation());
    } else {
      userDto = userDtoService.translateDtoOutput(user, userDto.getHouseLocation());
    }
    ResponseEntity<?> responseEntity = new ResponseEntity<>(userDto, HttpStatus.OK);
    trace("updateUser output:" + responseEntity);
    return responseEntity;
  }

  /**
   * Method controller for patching a currently existing user in the database. This patch method
   * will be used by an admin to invalidate a user. The method turns a user Data Transfer object
   * from the request body into a User object. It then calls the service method to update that user
   * by persisting any changes made to it in the database. the method needs to return a response
   * entity since we are utilizing DTOs. Logic flow is performed to find out whether the user has a
   * car or not and calls the appropriate overloaded method in the userDtoService class. Then it
   * returns the DTO in a response entity.
   * -------------------------------------------------------------------------------------------
   * Here, the front-end asks the back-end to update a user to be either active or inactive. The
   * front-end passes in a email allowing for the identification of the user object associated and a
   * userDTO object. This information is used to update the user's information in the back-end.
   * Returns a userDTO object and HttpStatus code. Note: Available to admins only!
   */
  @Override
  @PatchMapping("/user/{email}")
  public ResponseEntity<?> activateOrInactivateUser(@PathVariable String email,
      @RequestBody UserDto userDto) {
    trace("activateOrInactivateUser input:" + email + ", " + userDto);
    User user = userDtoService.translateDtoInput(userDto);
    user = userService.getUserByEmail(user.getEmail());
    user = userService.updateUser(user);
    Car car = carService.getCarByEmail(user.getEmail());
    if (car != null) {
      userDto = userDtoService.translateDtoOutput(user, car, userDto.getHouseLocation());
    } else {
      userDto = userDtoService.translateDtoOutput(user, userDto.getHouseLocation());
    }
    ResponseEntity<?> responseEntity = new ResponseEntity<>(userDto, HttpStatus.OK);
    trace("activateOrInactivateUser output:" + responseEntity);
    return responseEntity;
  }

  /**
   * Method controller for getting all users by their role. Here the front-end asks the back-end to
   * return a userDTOList object with a HttpStatus code. When the /user/ endpoint is hit,
   * getALLUsers() is called which simply grabs all user objects in the back-end and translates them
   * into a userDto list object that is returned in a response entity. A DTO stores information that
   * is ready to be passed back to the front-end.
   */
  @Override
  @GetMapping("/user/")
  public ResponseEntity<?> getAllUsersByRole(@QueryParam("role") Role role) {
    trace("getAllUsersByRole input:" + role);
    List<User> allUsersByRole = userService.getAllUsersByRole(role);
    List<UserDto> userDtoList = userDtoService.translateDtoOutput(allUsersByRole);
    ResponseEntity<?> responseEntity = new ResponseEntity<>(userDtoList, HttpStatus.OK);
    trace("getAllUsersByRole output:" + responseEntity);
    return responseEntity;
  }

  /**
   * Method controller for getting all users that exist in the database. Here the front-end asks the
   * back-end to return a userDTOList object with a HttpStatus code. When the /user endpoint is hit,
   * getALLUsers() is called which simply grabs all user objects in the back-end and translates them
   * to return a userDto list object in a response entity. A DTO stores information that is ready to
   * be passed back to the front-end.
   */
  @Override
  @GetMapping("/user")
  public ResponseEntity<?> getAllUsers() {
    trace("getAllUsers input:");
    List<User> allUsers = userService.getAllUsers();
    List<UserDto> userDtoList = userDtoService.translateDtoOutput(allUsers);
    ResponseEntity<?> responseEntity = new ResponseEntity<>(userDtoList, HttpStatus.OK);
    trace("getAllUsers output:" + responseEntity);
    return responseEntity;
  }


  /**
   * Method controller for getting a specific user by their email address. It gets a user and their
   * car by the email address that was passed in. The method needs to return a response entity since
   * we are utilizing DTOs. Logic flow is performed to find out whether the user has a car or not
   * and calls the appropriate overloaded method in the userDtoService class. Then it returns the
   * DTO in a response entity. Here the front-end asks the back-end to return a userDTO object and a
   * HttpStatus code.
   */
  @Override
  @GetMapping("/user/{email}")
  public ResponseEntity<?> getUser(@PathVariable String email) {
    trace("getUser input:" + email);
    User user = userService.getUserByEmail(email);
    Car car = carService.getCarByEmail(email);
    UserDto userDto = userDtoService.translateDtoOutput(user, car);
    ResponseEntity<?> responseEntity = new ResponseEntity<>(userDto, HttpStatus.OK);
    trace("getUser output:" + responseEntity);
    return responseEntity;
  }

}
