package com.revature.service;

import com.revature.bean.User;
import com.revature.bean.User.Role;

import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  User createUser(User user);

  User updateUser(User user);

  User patchUser(User user); // added

  List<User> getAllUsersByRole(Role role);

  User getUserByEmail(String email); // added

}
