package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Car;
import com.revature.bean.HouseLocationDto;
import com.revature.bean.User;
import com.revature.bean.User.Role;
import com.revature.bean.UserDto;
import com.revature.service.CarService;
import com.revature.service.UserDtoService;
import com.revature.service.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.HttpMethod;
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
  // @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createUser(@RequestBody(required = false) UserDto userDto) {
    System.out.println("Hit UserControllerImpl /user POST method.");
    System.out.println("UserDto:" + userDto);
    // Creates a new user object. Default account status is true.
    User user = userDtoService.translateDtoInput(userDto);
    System.out.println("User:" + user);

    String host = "localhost";
    String port = "8089";

    try {
      // Opening new HTTP Request to the location service to have it return a HousingDto.
      URL obj;
      obj = new URL("HTTP://" + host + ":" + port + "/housing-location");
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod(HttpMethod.GET);
      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        // If the response code is an "OK".
        // Print the response. 
        System.out.println("User response was Ok.");

        // Get and print the response body.
        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
          sb.append(output);
        }
        System.out.println("JSON: " + sb);

        // Turn the JSON into an array of HouseLocationDtos.

        try {
          ObjectMapper om = new ObjectMapper();
          HouseLocationDto[] arrLoc = om.readValue(sb.toString(), HouseLocationDto[].class);
          System.out.println("Array of Locations: " + arrLoc);
        } catch (Exception e) {
          e.printStackTrace();
        }

        //return new ResponseEntity(HttpStatus.OK);
      } else {
        // If the response was not an "OK", print the response code and tell the user.
        System.out.println("Request did not work. Status Code: " + responseCode);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    user = userService.createUser(user);

    Car car = new Car(0, user.getUserID(), userDto.getCarDto().getSeatNumber());
    car = carService.createCar(car);

    UserDto response = userDtoService.translateDtoOutput(user, car);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Override
  @PutMapping("/user/{email}")
  public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
    User user = userDtoService.translateDtoInput(userDto);
    user = userService.updateUser(user);
    Car car = carService.getCarByEmail(user.getEmail());
    userDto = userDtoService.translateDtoOutput(user, car);
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }

  @Override
  @PatchMapping("/user/{email}") // only available to admin
  public ResponseEntity<?> activateOrInactivateUser(@PathVariable String email,
      @RequestBody UserDto userDto) {
    User user = userDtoService.translateDtoInput(userDto);
    user = userService.patchUser(user);
    Car car = carService.getCarByEmail(user.getEmail());
    userDto = userDtoService.translateDtoOutput(user, car);
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }

  @Override
  @GetMapping("/user/")
  public ResponseEntity<List<User>> getAllUsersByRole(@QueryParam("role") Role role) {
    List<User> allUsersByRole = userService.getAllUsersByRole(role);
    return new ResponseEntity<>(allUsersByRole, HttpStatus.OK);
  }

  @Override
  @GetMapping("/user")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> allUsers = userService.getAllUsers();

    return new ResponseEntity<>(allUsers, HttpStatus.OK);
  }

  @Override
  @GetMapping("/user/{email}")
  public ResponseEntity<?> getUser(@PathVariable String email) {
    User user = userService.getUserByEmail(email);
    Car car = carService.getCarByEmail(email);
    UserDto userDto = userDtoService.translateDtoOutput(user, car);
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }

}
