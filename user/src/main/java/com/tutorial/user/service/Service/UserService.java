package com.tutorial.user.service.Service;

import com.tutorial.user.service.Entity._User;
import com.tutorial.user.service.FeignClients.BikeFeignClient;
import com.tutorial.user.service.FeignClients.CarFeignClient;
import com.tutorial.user.service.Model.Bike;
import com.tutorial.user.service.Model.Car;
import com.tutorial.user.service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<_User> getAll(){
        return userRepository.findAll();
    }

    public _User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public _User save(_User user){
        return userRepository.save(user);
    }

    public List<Car> getCars(int userId){
        //List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
        List<Car> cars = restTemplate.getForObject("http://car-service/car/byuser/" + userId, List.class);
        return cars;
    }
    public List<Bike> getBikes(int userId){
        //List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/byuser/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike newBike = bikeFeignClient.save(bike);
        return newBike;
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();
        _User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            result.put("Mensaje", "NO EXISTE EL USUARIO");
            return result;
        }
        result.put("Usuario", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars == null)
            result.put("CARS","EL USUARIO NO TIENE CARS ASIGNADO");
        else
            result.put("CARS", cars);
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes == null)
            result.put("BIKES","EL USUARIO NO TIENE BIKES ASIGNADAS");
        else
            result.put("BIKES", bikes);
        return result;
    }
}
