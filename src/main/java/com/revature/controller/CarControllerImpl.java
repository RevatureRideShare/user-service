package com.revature.controller;

import static com.revature.util.LoggerUtil.trace;

import com.revature.bean.Car;
import com.revature.service.CarServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CarControllerImpl implements CarController {
  CarServiceImpl carServiceImpl;

  @Autowired
  public void setCarServiceImpl(CarServiceImpl carServiceImpl) {
    this.carServiceImpl = carServiceImpl;
  }

  // This controller method creates a car in the database by calling the appropriate service method.
  @Override
  @PostMapping("/car")
  @ResponseStatus(HttpStatus.CREATED)
  public Car createCar(Car car) {
    trace("createCar input:" + car);
    Car createdCar = carServiceImpl.createCar(car);
    trace("createCar output:" + createdCar);
    return createdCar;
  }

  // This controller method gets a car from the database by calling the appropriate service method.
  @Override
  @GetMapping("/car/{email}/car")
  public Car getCar(@PathVariable String email) {
    trace("getCar input:" + email);
    Car getCar = carServiceImpl.getCarByEmail(email);
    trace("getCar output:" + getCar);
    return getCar;
  }

}
