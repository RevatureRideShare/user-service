package com.revature.service;

import com.revature.bean.User;
import com.revature.bean.User.Role;
import java.util.List;

/**
 * Service for getting and creating/updating users. Implemented by UserServiceImpl.java.
 */
public interface UserService {

  List<User> getAllUsers();

  User createUser(User user);

  User updateUser(User user);

  User patchUser(User user);

  List<User> getAllUsersByRole(Role role);

  User getUserByEmail(String email);

}
