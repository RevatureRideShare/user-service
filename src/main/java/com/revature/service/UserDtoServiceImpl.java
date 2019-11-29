package com.revature.service;

import com.revature.bean.Car;
import com.revature.bean.CarDto;
import com.revature.bean.HouseLocationDto;
import com.revature.bean.User;
import com.revature.bean.UserDto;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDtoServiceImpl implements UserDtoService {

  @Override
  public User translateDtoInput(UserDto userDto) {
    User user = new User(0, userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(),
        userDto.getPhoneNumber(), userDto.getRideStatus(), userDto.getRole(),
        userDto.isAccountStatus(), 0);
    return user;
  }

  @Override
  public UserDto translateDtoOutput(User user, Car car) {
    CarDto carDto = new CarDto(car.getSeatNumber());

    // Need to get houseLocationDto from location service. 
    // Should get the houseLocationDto from the location service based on the housingLocationId.
    HouseLocationDto houseLocationDto = null;

    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocationDto, carDto);
    return userDto;
  }

  @Override
  public UserDto translateDtoOutput(User user, Car car, HouseLocationDto houseLocationDto) {
    CarDto carDto = new CarDto(car.getSeatNumber());

    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocationDto, carDto);
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
