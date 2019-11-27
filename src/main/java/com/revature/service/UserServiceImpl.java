package com.revature.service;

import com.revature.bean.User;
import com.revature.bean.User.Role;
import com.revature.exception.UpdateNonexistentException;
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
    if ("".equals(user.getEmail()) || user.getEmail() == null) {
      throw new NullPointerException("Email address cannot be null");
    } else {
      if (userRepo.findByEmail(user.getEmail()) != null) {
        throw new DuplicateKeyException("User already exists");
      } else {
        return userRepo.save(user);
      }
    }
  }

  @Override
  public User updateUser(User user) {
    if (user.getEmail() == null) {
      throw new UpdateNonexistentException("This user does not exist");
    } else {
      return userRepo.save(user);
    }
  }

  @Override
  public User patchUser(User user) {
    if (user.getEmail() == null) {
      throw new UpdateNonexistentException("This user does not exist");
    } else {
      return userRepo.save(user);
    }
  }

  @Override
  public User getUserByEmail(String email) {

    if ("".equals(email) || email == null) {
      throw new NullPointerException("Email address cannot be null");
    } else {
      return userRepo.findByEmail(email);
    }
  }

}
