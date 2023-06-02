package com.tutorial.car.service.Controller;

import com.tutorial.car.service.Entity.Car;
import com.tutorial.car.service.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> all = carService.getAll();
        if(all.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getUserById(@PathVariable("id") int id){
        Car mister = carService.getUserById(id);
        if(mister == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mister);
    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car user){
        Car mister = carService.save(user);
        return ResponseEntity.ok(mister);
    }
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> findByUserId(@PathVariable("userId") int userId){
        List<Car> all = carService.findByUserId(userId);
        if(all.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
    }
}
