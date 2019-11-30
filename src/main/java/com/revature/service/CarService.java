package com.revature.service;

import com.revature.bean.Car;

import java.util.List;

public interface CarService {

  Car getCarByEmail(String email);

  Car createCar(Car car);

  List<Car> getAllCars();

}
