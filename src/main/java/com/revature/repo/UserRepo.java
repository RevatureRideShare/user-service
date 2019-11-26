package com.revature.repo;

import com.revature.bean.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

  public User findByEmail(String email);

  public List<User> findAllByRole(String role);

}
