package com.revature.controller;

import com.revature.bean.User.Role;
import com.revature.bean.UserDto;

import java.util.List;

public interface UserController {

  public UserDto createUser(UserDto userDto);

  public UserDto updateUser(String email, UserDto userDto);

  public UserDto activateOrInactivateUser(String email, UserDto userDto);

  public List<UserDto> getAllUsersByRole(Role role);

  public List<UserDto> getAllUsers();

  public UserDto getUser(String email);

}
