package com.revature.service;

import static com.revature.util.LoggerUtil.trace;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserDtoServiceImpl implements UserDtoService {

  @Value("#{environment.RIDESHARE_1909_LOCATION_HOST}")
  private String locationHost;
  @Value("#{environment.RIDESHARE_1909_LOCATION_PORT}")
  private String locationPort;

  private CarService carService;

  @Autowired
  public void setCarService(CarService carService) {
    this.carService = carService;
  }

  @Override
  public User translateDtoInput(UserDto userDto) {
    trace("translateDtoOutput input: " + userDto);
    User user = new User(0, userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(),
        userDto.getPhoneNumber(), userDto.getRideStatus(), userDto.getRole(),
        userDto.isAccountStatus(), userDto.getHouseLocation().getlocationID());
    trace("translateDtoOutput output: " + user);
    return user;
  }

  @Override
  public UserDto translateDtoOutput(User user, Car car) {
    trace("translateDtoOutput input: " + user + ", " + car);
    CarDto carDto = new CarDto(car.getSeatNumber());

    // Need to get houseLocation from location service.
    // Should get the houseLocation from the location service based on the housingLocationId.
    HouseLocation houseLocation = null;

    try {
      // Opening new HTTP Request to the location service to have it gets the appropriate housing
      // location.
      URL obj = new URL("HTTP://" + locationHost + ":" + locationPort + "/housing-location/"
          + user.getLocationID());
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
        trace("translateDtoOutput output: BadRequestException");
        throw new BadRequestException();
      }

    } catch (Exception e) {
      e.printStackTrace();
      trace("translateDtoOutput output: BadRequestException");
      throw new BadRequestException();
    }

    // Build new userDto object.
    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocation, carDto);
    trace("translateDtoOutput output: " + userDto);
    return userDto;
  }

  @Override
  public UserDto translateDtoOutput(User user, HouseLocation houseLocation) {
    trace("translateDtoOutput input: " + user + ", " + houseLocation);
    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocation, null);
    trace("translateDtoOutput output: " + userDto);
    return userDto;
  }

  @Override
  public UserDto translateDtoOutput(User user, Car car, HouseLocation houseLocation) {
    trace("translateDtoOutput input: " + user + ", " + car + ", " + houseLocation);
    CarDto carDto = new CarDto(car.getSeatNumber());

    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocation, carDto);
    trace("translateDtoOutput input: " + userDto);
    return userDto;
  }

  @Override
  public List<UserDto> translateDtoOutput(List<User> listUser) {
    trace("translateDtoOutput input: " + listUser);
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
    trace("translateDtoOutput output: " + translatedUsers);
    return translatedUsers;
  }

}
