package com.revature.service;

import com.revature.bean.User;
import com.revature.repo.UserRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

public class UserServiceImpl implements UserService {

  private UserRepo userRepo;

  @Autowired
  public void setUserRepo(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public List<User> getAllUsers() {

    return userRepo.findAll();
  }

  @Override
  public List<User> getAllUsersByRole(String role) {

    return userRepo.findAllByRole(role);
  }

  @Override
  public User createUser(User user) {
    if (userRepo.findById(user.getUserID()).isPresent()) {
      throw new DuplicateKeyException("Object already exists in database");
    } else {
      return userRepo.save(user);
    }
  }

  @Override
  public User updateUser(User user) {

    return userRepo.save(user);
  }

  @Override
  public User patchUser(User user) {

    return userRepo.save(user);
  }

  @Override
  public User getUserByEmail(String email) {

    return userRepo.findByEmail(email);
  }

}
