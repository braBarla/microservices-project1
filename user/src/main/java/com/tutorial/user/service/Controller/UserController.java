package com.tutorial.user.service.Controller;

import com.tutorial.user.service.Entity._User;
import com.tutorial.user.service.Model.Bike;
import com.tutorial.user.service.Model.Car;
import com.tutorial.user.service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<_User>> getAll(){
        List<_User> all = userService.getAll();
        if(all.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<_User> getUserById(@PathVariable("id") int id){
        _User mister = userService.getUserById(id);
        if(mister == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mister);
    }

    @PostMapping()
    public ResponseEntity<_User> save(@RequestBody _User user){
        _User mister = userService.save(user);
        return ResponseEntity.ok(mister);
    }
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        _User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        _User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> save(@PathVariable("userId") int userId, @RequestBody Car car){
        _User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        Car carNew = userService.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> save(@PathVariable("userId") int userId, @RequestBody Bike bike){
        _User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        Bike newBike = userService.saveBike(userId, bike);
        return ResponseEntity.ok(newBike);
    }
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }
}