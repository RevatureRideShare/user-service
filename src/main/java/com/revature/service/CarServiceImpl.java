package com.revature.service;

import static com.revature.util.LoggerUtil.trace;
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

/**
 * Service for getting and creating cars.
 */
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
    trace("getCarByEmail input: " + email);
    User user = userRepo.findByEmail(email);
    Car getCar = carRepo.findByUserID(user.getUserID());
    trace("getCarByEmail output: " + getCar);
    return getCar;

  }

  /**
   * This method gets a car by the carID in the database and then returns that car. We utilized
   * Optional to account for having a null car.
   */
  @Override
  public Optional<Car> getCarByID(int carID) {
    trace("getCarByID input: " + carID);
    Optional<Car> getCar = carRepo.findById(carID);
    trace("getCarByID output: " + getCar);
    return getCar;
  }

  /**
   * This method creates a car in the database. If the car already exists, it throws a
   * DuplicateKeyException. If the exception is actually a constrain violation, it throws the
   * ConstrainViolationException instead.
   */
  @Override
  public Car createCar(Car car) {
    trace("createCar input: " + car);
    if (getCarByID(car.getCarID()).isPresent()) {
      throw new DuplicateKeyException("Object already exists in database");
    } else {
      try {
        Car createCar = carRepo.save(car);
        trace("createCar output: " + car);
        return createCar;
      } catch (TransactionSystemException t) {
        Throwable myT = t.getCause().getCause();
        if (myT instanceof ConstraintViolationException) {
          trace("createCar output: Exception " + myT);
          throw ((ConstraintViolationException) myT);
        }
        trace("createCar output: Exception " + t);
        throw t;
      }
    }
  }

  // This method gets all cars in the database and then returns that list.
  @Override
  public List<Car> getAllCars() {
    trace("getAllCars input: ");
    List<Car> allCarList = carRepo.findAll();
    trace("getAllCars output: " + allCarList);
    return allCarList;
  }

}
