package com.revature.controller;

import com.revature.bean.Car;
import com.revature.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CarControllerImpl implements CarController {

  CarService carService;

  @Autowired
  public void setCarService(CarService carService) {
    this.carService = carService;
  }

  @Override
  @PostMapping("/car")
  @ResponseStatus(HttpStatus.CREATED)
  public Car createCar(Car car) {

    return carService.createCar(car);
  }

  @Override
  @GetMapping("/car/{email}/car")
  public Car getCar(@PathVariable String email) {

    return carService.getCarByEmail(email);
  }

}
