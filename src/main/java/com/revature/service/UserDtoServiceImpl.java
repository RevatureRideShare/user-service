package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Car;
import com.revature.bean.CarDto;
import com.revature.bean.HouseLocation;
import com.revature.bean.User;
import com.revature.bean.UserDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDtoServiceImpl implements UserDtoService {

  private CarService carService;

  @Autowired
  public void setCarService(CarService carService) {
    this.carService = carService;
  }

  @Override
  public User translateDtoInput(UserDto userDto) {
    User user = new User(0, userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(),
        userDto.getPhoneNumber(), userDto.getRideStatus(), userDto.getRole(),
        userDto.isAccountStatus(), userDto.getHouseLocation().getlocationID());
    return user;
  }

  @Override
  public UserDto translateDtoOutput(User user, Car car) {
    CarDto carDto = new CarDto(car.getSeatNumber());

    String host = "localhost";
    String port = "8089";

    // Need to get houseLocation from location service.
    // Should get the houseLocation from the location service based on the housingLocationId.
    HouseLocation houseLocation = null;

    try {
      // Opening new HTTP Request to the location service to have it gets the appropriate housing
      // location.
      URL obj =
          new URL("HTTP://" + host + ":" + port + "/housing-location/" + user.getLocationID());
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod(HttpMethod.GET);

      // Sending HTTP Request.
      // Reading response.
      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {

        // If the response code is an "OK".
        // Get the response body.
        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
          sb.append(output);
        }

        // Translate from JSON into HouseLocation.
        ObjectMapper om = new ObjectMapper();
        houseLocation = om.readValue(sb.toString(), HouseLocation.class);

      } else {
        throw new BadRequestException();
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw new BadRequestException();
    }

    // Build new userDto object.
    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocation, carDto);
    return userDto;
  }

  @Override
  public UserDto translateDtoOutput(User user, HouseLocation houseLocation) {

    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocation, null);
    return userDto;
  }

  @Override
  public UserDto translateDtoOutput(User user, Car car, HouseLocation houseLocation) {
    CarDto carDto = new CarDto(car.getSeatNumber());

    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocation, carDto);
    return userDto;
  }

  @Override
  public List<UserDto> translateDtoOutput(List<User> listUser) {

    List<UserDto> translatedUsers = new LinkedList<>();

    // Get list of cars from the database.
    List<Car> allCars = carService.getAllCars();

    // Take each user object, combine with relevant car, run it through the
    // trainslateDtoOutput method to get a UserDto back.
    for (User u : listUser) {
      for (Car c : allCars) {
        if (c.getUserID() == u.getUserID()) {
          // Append each returned UserDto to a UserDto list.
          translatedUsers.add(translateDtoOutput(u, c));
          break;
        }
      }
    }

    // Return UserDto list.
    return translatedUsers;
  }

}
