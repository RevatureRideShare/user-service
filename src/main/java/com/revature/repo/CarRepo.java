package com.revature.repo;

import com.revature.bean.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The car repository is for communication with the database table for cars.
 */
@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

  public Car findByUserID(int userID);

}
