package com.tutorial.bike.service.Controller;


import com.tutorial.bike.service.Entity.Bike;
import com.tutorial.bike.service.Service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> all = bikeService.getAll();
        if(all.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getUserById(@PathVariable("id") int id){
        Bike mister = bikeService.getUserById(id);
        if(mister == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mister);
    }

    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike user){
        Bike mister = bikeService.save(user);
        return ResponseEntity.ok(mister);
    }
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> findByUserId(@PathVariable("userId") int userId){
        List<Bike> all = bikeService.findByUserId(userId);
        if(all.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
    }
}
