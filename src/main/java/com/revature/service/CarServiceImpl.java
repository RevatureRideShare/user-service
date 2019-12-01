package com.revature.service;

import com.revature.bean.Car;
import com.revature.bean.User;
import com.revature.repo.CarRepo;
import com.revature.repo.UserRepo;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

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
  public Optional<Car> getCarByID(int carID) {
    return carRepo.findById(carID);
  }

  @Override
  public Car createCar(Car car) {
    if (getCarByID(car.getCarID()).isPresent()) {
      throw new DuplicateKeyException("Object already exists in database");
    } else {
      try {
        return carRepo.save(car);
      } catch (TransactionSystemException t) {
        Throwable myT = t.getCause().getCause();
        if (myT instanceof ConstraintViolationException) {
          throw ((ConstraintViolationException) myT);
        }
        throw t;
      }
    }
  }

  @Override
  public List<Car> getAllCars() {
    return carRepo.findAll();
  }

}
