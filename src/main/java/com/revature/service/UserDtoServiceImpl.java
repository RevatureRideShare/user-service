package com.revature.service;

import com.revature.bean.Car;
import com.revature.bean.User;
import com.revature.bean.UserDto;

import org.springframework.stereotype.Service;

@Service
public class UserDtoServiceImpl implements UserDtoService {

  @Override
  public User translateDtoInput(UserDto userDto) {
    User user = new User(0, userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(),
        userDto.getPhoneNumber(), userDto.getRideStatus(), userDto.getRole(), true, 0);
    return user;
  }

  @Override
  public UserDto translateDtoOutput(User user, Car car) {
    UserDto userDto = new UserDto()
    return userDto;
  }

}
