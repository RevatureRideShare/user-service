package com.revature.service;

import com.revature.bean.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

  Car getCarByEmail(String email);

  Car createCar(Car car);

  Optional<Car> getCarByID(int carID);

  List<Car> getAllCars();

}
