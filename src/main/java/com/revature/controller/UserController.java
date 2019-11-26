package com.revature.controller;

import com.revature.bean.User;
import com.revature.bean.User.Role;

import java.util.List;

public interface UserController {

  public User createUser(User user);

  public User updateUser(String email, User user);

  public User activateOrInactivateUser(String email, User user);

  public List<User> getAllUsersByRole(Role role);

  public List<User> getAllUsers();

  public User getUser(String email);

}
