package com.revature.controller;

import com.revature.bean.User;
import com.revature.bean.User.Role;
import com.revature.service.UserService;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

  UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  @PostMapping("/user")
  @ResponseStatus(HttpStatus.CREATED)
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @Override
  @PutMapping("/user/{email}")
  public User updateUser(@PathVariable String email, @RequestBody User user) {
    return userService.updateUser(user);
  }

  @Override
  @PatchMapping("/user/{email}") // only available to admin
  public User activateOrInactivateUser(@PathVariable String email, @RequestBody User user) {
    return userService.patchUser(user);
  }

  @Override
  @GetMapping("/user/")
  public List<User> getAllUsersByRole(@QueryParam("role") Role role) {
    return userService.getAllUsersByRole(role);
  }

  @Override
  @GetMapping("/user")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @Override
  @GetMapping("/user/{email}")
  public User getUser(@PathVariable String email) {
    return userService.getUserByEmail(email);
  }

}
