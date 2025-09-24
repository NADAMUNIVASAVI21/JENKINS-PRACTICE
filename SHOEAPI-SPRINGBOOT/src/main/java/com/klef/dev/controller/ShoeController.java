package com.klef.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.dev.entity.Shoe;
import com.klef.dev.service.ShoeService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ShoeController {

    @Autowired
    private ShoeService shoeService;

    @GetMapping("/")
    public String home() {
        return "Shoe Management API is running";
    }

    // Add Shoe
    @PostMapping("/add")
    public ResponseEntity<Shoe> addShoe(@RequestBody Shoe shoe) {
        Shoe savedShoe = shoeService.addShoe(shoe);
        return new ResponseEntity<>(savedShoe, HttpStatus.CREATED);
    }

    // Get All Shoes
    @GetMapping("/all")
    public ResponseEntity<List<Shoe>> getAllShoes() {
        List<Shoe> shoes = shoeService.getAllShoes();
        return new ResponseEntity<>(shoes, HttpStatus.OK);
    }

    // Get Shoe by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getShoeById(@PathVariable int id) {
        Shoe shoe = shoeService.getShoeById(id);
        if (shoe != null) {
            return new ResponseEntity<>(shoe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Shoe with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Update Shoe
    @PutMapping("/update")
    public ResponseEntity<?> updateShoe(@RequestBody Shoe shoe) {
        Shoe existing = shoeService.getShoeById(shoe.getId());
        if (existing != null) {
            Shoe updatedShoe = shoeService.updateShoe(shoe);
            return new ResponseEntity<>(updatedShoe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Shoe with ID " + shoe.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Delete Shoe
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShoe(@PathVariable int id) {
        Shoe existing = shoeService.getShoeById(id);
        if (existing != null) {
            shoeService.deleteShoeById(id);
            return new ResponseEntity<>("Shoe with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Shoe with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
