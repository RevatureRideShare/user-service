package com.revature.service;

import com.revature.bean.Car;
import com.revature.bean.HouseLocation;
import com.revature.bean.User;
import com.revature.bean.UserDto;
import java.util.List;

/**
 * This service is for translating user DTO's to java objects or visa-versa. DTO's allow for easy
 * data communication between the front and back ends of the application. Implemented by
 * UserDtoServiceImpl.java.
 */
public interface UserDtoService {

  User translateDtoInput(UserDto userDto);

  UserDto translateDtoOutput(User user, Car car);

  UserDto translateDtoOutput(User user, HouseLocation houseLocation);

  public UserDto translateDtoOutput(User user, Car car, HouseLocation houseLocation);

  public List<UserDto> translateDtoOutput(List<User> listUser);

}
