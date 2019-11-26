package com.revature.service;

import com.revature.bean.Car;

public interface CarService {

  Car getCarByEmail(String email);

  Car createCar(Car car);

}
