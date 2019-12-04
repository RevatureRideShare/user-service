package com.revature.controller;

import com.revature.bean.Car;

/**
 * A controller allows for communication between micro-services and to the front-end. This
 * controller is for a user's car information. Implemented by CarControllerImpl.java.
 */
public interface CarController {

  public Car createCar(Car car);

  public Car getCar(String email);

}
