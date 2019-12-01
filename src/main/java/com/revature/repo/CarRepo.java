package com.revature.repo;

import com.revature.bean.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

  public Car findByUserID(int userID);

}
