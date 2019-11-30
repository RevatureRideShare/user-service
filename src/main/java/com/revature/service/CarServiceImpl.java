package com.revature.service;

import com.revature.bean.Car;
import com.revature.bean.User;
import com.revature.repo.CarRepo;
import com.revature.repo.UserRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

  private CarRepo carRepo;

  @Autowired
  public void setUserRepo(CarRepo carRepo) {
    this.carRepo = carRepo;
  }

  private UserRepo userRepo;

  @Autowired
  public void setUserRepo(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public Car getCarByEmail(String email) {

    User user = userRepo.findByEmail(email);
    return carRepo.findByUserID(user.getUserID());

  }

  @Override
  public Car createCar(Car car) {

    return carRepo.save(car);
  }

  @Override
  public List<Car> getAllCars() {
    return carRepo.findAll();
  }

}
