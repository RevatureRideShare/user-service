package com.revature.service;

import com.revature.bean.Car;
import com.revature.bean.HouseLocation;
import com.revature.bean.User;
import com.revature.bean.UserDto;

import java.util.List;

public interface UserDtoService {

  User translateDtoInput(UserDto userDto);

  UserDto translateDtoOutput(User user, Car car);

  public UserDto translateDtoOutput(User user, Car car, HouseLocation houseLocation);

  public List<UserDto> translateDtoOutput(List<User> listUser);

}