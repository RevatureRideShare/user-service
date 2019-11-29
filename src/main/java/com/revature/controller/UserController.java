package com.revature.controller;

import com.revature.bean.User.Role;
import com.revature.bean.UserDto;

import org.springframework.http.ResponseEntity;

public interface UserController {

  public ResponseEntity createUser(UserDto userDto);

  public ResponseEntity updateUser(String email, UserDto userDto);

  public ResponseEntity activateOrInactivateUser(String email, UserDto userDto);

  public ResponseEntity getAllUsersByRole(Role role);

  public ResponseEntity getAllUsers();

  public ResponseEntity getUser(String email);

}
