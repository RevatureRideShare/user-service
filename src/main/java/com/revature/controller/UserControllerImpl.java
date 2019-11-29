package com.revature.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

  @Override
  @PostMapping("/user")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity createUser(@RequestBody UserDto userDto) {
    System.out.println("Hit UserControllerImpl /user POST method.");
    System.out.println(userDto);
    // Creates a new user object. Default account status is true.
    User user = userDtoService.translateDtoInput(userDto);
    System.out.println(user);

    String host = "localhost";
    String port = "8089";
    //    try {
    //      // Opening new HTTP Request to the location service to have it return a HousingDto.
    //      URL obj;
    //      obj = new URL("HTTP://" + host + ":" + port + "/housing-location");
    //      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    //      con.setRequestMethod(HttpMethod.POST);
    //      int responseCode = con.getResponseCode();
    //      if (responseCode == HttpURLConnection.HTTP_OK) {
    //        // If the response code is an "OK".
    //        // Print the response. 
    //        System.out.println("User response was Ok.");
    //        //con.getR
    //        //return new ResponseEntity(HttpStatus.OK);
    //      } else {
    //        // If the response was not an "OK", print the response code and tell the user.
    //        System.out.println("Request did not work. Status Code: " + responseCode);
    //        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    //      }
    //    } catch (Exception e) {
    //      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    //    }


    user = userService.createUser(user);

    Car car = new Car(0, user.getUserID(), userDto.getCarDto().getSeatNumber());
    car = carService.createCar(car);

    UserDto response = userDtoService.translateDtoOutput(user, car);
    return new ResponseEntity(response, HttpStatus.CREATED);
  }

  @Override
  @PutMapping("/user/{email}")
  public User updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
    return userService.updateUser(user);
  }

  @Override
  @PatchMapping("/user/{email}") // only available to admin
  public User activateOrInactivateUser(@PathVariable String email, @RequestBody UserDto userDto) {
    return userService.patchUser(user);
  }

  @Override
  @GetMapping("/user/")
  public List<User> getAllUsersByRole(@QueryParam("role") Role role) {
    return userService.getAllUsersByRole(role);
  }

  @Override
  @GetMapping("/user")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @Override
  @GetMapping("/user/{email}")
  public User getUser(@PathVariable String email) {
    return userService.getUserByEmail(email);
  }

}
