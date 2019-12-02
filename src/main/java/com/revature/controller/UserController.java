package com.revature.controller;

import com.revature.bean.User.Role;
import com.revature.bean.UserDto;
import org.springframework.http.ResponseEntity;

/**
 * A controller allows for communication between micro-services and to the front-end. This
 * controller is for management of users information. Implemented by UserControllerImpl.java.
 */
public interface UserController {

  public ResponseEntity<?> createUser(UserDto userDto);

  public ResponseEntity<?> updateUser(String email, UserDto userDto);

  public ResponseEntity<?> activateOrInactivateUser(String email, UserDto userDto);

  public ResponseEntity<?> getAllUsersByRole(Role role);

  public ResponseEntity<?> getAllUsers();

  public ResponseEntity<?> getUser(String email);

}
