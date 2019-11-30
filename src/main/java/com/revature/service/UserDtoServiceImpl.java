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
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.HttpMethod;

import org.springframework.stereotype.Service;

@Service
public class UserDtoServiceImpl implements UserDtoService {

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
      System.out
          .println("HTTP://" + host + ":" + port + "/housing-location/" + user.getLocationID());
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod(HttpMethod.GET);

      // Sending HTTP Request.
      //      con.setDoOutput(true);
      //      OutputStream os = con.getOutputStream();
      //      OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
      //      osw.flush();
      //      osw.close();
      //      os.close();
      //      System.out.println("Closed streams.");

      // Reading response.
      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {

        // If the response code is an "OK".
        // Print the response code.
        System.out.println("Request was successful. Status Code: " + responseCode + ".");

        // Get and print the response body.
        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
          sb.append(output);
        }
        System.out.println(sb);

        ObjectMapper om = new ObjectMapper();
        houseLocation = om.readValue(sb.toString(), HouseLocation.class);

      } else {
        throw new BadRequestException();
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw new BadRequestException();
    }


    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocation, carDto);
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

    // Get list of houseLocationDtos from the location service.
    // Get list of cars from the database.
    // Take each user object, combine with relevant houseLocationDto and car, run it through the
    // trainslateDtoOutput method to get a UserDto back.
    // Append each returned UserDto to a UserDto list.
    // Return UserDto list.
    return null;
  }

}
