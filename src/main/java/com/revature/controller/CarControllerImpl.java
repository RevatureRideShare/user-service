package com.revature.controller;

import com.revature.bean.Car;
import com.revature.service.CarServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarControllerImpl implements CarController {

  CarServiceImpl carServiceImpl;

  @Autowired
  public void setCarServiceImpl(CarServiceImpl carServiceImpl) {
    this.carServiceImpl = carServiceImpl;
  }

  @Override
  @PostMapping("/car")
  @ResponseStatus(HttpStatus.CREATED)
  public Car createCar(Car car) {
    return carServiceImpl.createCar(car);
  }

  @Override
  @GetMapping("/car/{email}/car")
  public Car getCar(@PathVariable String email) {
    return carServiceImpl.getCarByEmail(email);
  }

}
