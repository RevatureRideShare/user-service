package com.revature.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.bean.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID>{

}
