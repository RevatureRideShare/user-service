package com.revature.service;

import static com.revature.util.LoggerUtil.trace;
import com.revature.bean.User;
import com.revature.bean.User.Role;
import com.revature.exception.UpdateNonexistentException;
import com.revature.repo.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service implementation for getting and creating/updating users.
 */
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
    trace("getAllUsersByRole input: " + role);
    List<User> allUserList = userRepo.findAllByRole(role);
    trace("getAllUsersByRole input: " + allUserList);
    return allUserList;
  }

  @Override
  public User createUser(@RequestBody User user) {
    trace("createUser input: " + user);
    if ("".equals(user.getEmail()) || user.getEmail() == null) {
      throw new NullPointerException("Email address cannot be null");
    } else {
      if (userRepo.findByEmail(user.getEmail()) != null) {
        trace("getAllUsersByRole output: DuplicateKeyException");
        throw new DuplicateKeyException("User already exists");
      } else {
        User createUser = userRepo.save(user);
        trace("createUser output: " + createUser);
        return createUser;
      }
    }
  }

  @Override
  public User updateUser(User user) {
    trace("updateUser input: " + user);
    User existingUser = userRepo.findByEmail(user.getEmail());
    if (existingUser == null) {
      trace("updateUser output: UpdateNonexistentException");
      throw new UpdateNonexistentException("This user does not exist");
    } else {
      User updateUser = userRepo.save(user);
      trace("updateUser output: " + updateUser);
      return updateUser;
    }
  }

  @Override
  public User patchUser(User user) {
    trace("patchUser input: " + user);
    User existingUser = userRepo.findByEmail(user.getEmail());
    if (existingUser == null) {
      trace("patchUser output: UpdateNonexistentException");
      throw new UpdateNonexistentException("This user does not exist");
    } else {
      User patchUser = userRepo.save(user);
      trace("patchUser output: " + patchUser);
      return patchUser;
    }
  }

  @Override
  public User getUserByEmail(String email) {
    trace("getUserByEmail input: " + email);
    if ("".equals(email) || email == null) {
      trace("getUserByEmail output: NullPointerException");
      throw new NullPointerException("Email address cannot be null");
    } else {
      User getUser = userRepo.findByEmail(email);
      trace("getUserByEmail output: NullPointerException");
      return getUser;
    }
  }

}
