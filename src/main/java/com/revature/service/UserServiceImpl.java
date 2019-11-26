package com.revature.service;

import com.revature.bean.User;
import com.revature.bean.User.Role;
import com.revature.repo.UserRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
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
  public List<User> getAllUsersByRole(Role role) {
    return userRepo.findAllByRole(role);
  }

  @Override
  public User createUser(@RequestBody User user) {
    if (userRepo.findById(user.getUserID()).isPresent()) {
      System.out.println("Error");
      throw new DuplicateKeyException("User already exists in database");
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
