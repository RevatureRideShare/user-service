package com.revature.service;

import com.revature.bean.Car;
import com.revature.bean.CarDto;
import com.revature.bean.HouseLocationDto;
import com.revature.bean.User;
import com.revature.bean.UserDto;

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
    HouseLocationDto houseLocationDto = null;

    UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),
        user.getPhoneNumber(), user.getRideStatus().toString(), user.getRole().toString(),
        user.isAccountStatus(), houseLocationDto, carDto);
    return userDto;
  }

}
