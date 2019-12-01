package com.revature.controller;

import com.revature.bean.Car;

public interface CarController {

  public Car createCar(Car car);

  public Car getCar(String email);

}
