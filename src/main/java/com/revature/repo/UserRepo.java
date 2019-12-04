package com.revature.repo;

import com.revature.bean.User;
import com.revature.bean.User.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The user repository is for communication to the user table in the database.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

  public User findByEmail(String email);

  public List<User> findAllByRole(Role role);

}
