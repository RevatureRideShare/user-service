package com.revature.service;

import com.revature.bean.Car;
import com.revature.bean.User;
import com.revature.bean.UserDto;

public interface UserDtoService {

  User translateDtoInput(UserDto userDto);

  UserDto translateDtoOutput(User user, Car car);

}
