package com.tutorial.car.service.Service;

import com.tutorial.car.service.Entity.Car;
import com.tutorial.car.service.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getUserById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> findByUserId(int userId){
        return carRepository.findByUserId(userId);
    }
}
