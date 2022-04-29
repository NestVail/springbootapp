package com.test.springbootapp.controller;

import com.test.springbootapp.entity.Chicken;
import com.test.springbootapp.service.ChickenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chickens")
public class ChickenRestController {
    private ChickenService chickenService;

    // Construct Injection
    public ChickenRestController(ChickenService chickenService) {
        this.chickenService = chickenService;
    }

    @PostMapping("/add")
    public Chicken addChicken(@RequestBody Chicken chicken) {
        return chickenService.insertChicken(chicken);
    }

    @GetMapping
    public List<Chicken> getChickens(){
        return chickenService.selectAllChicken();
    }

    @GetMapping("/{id}")
    public Chicken getChicken(@PathVariable Long id) {
        return chickenService.selectChicken(id);
    }

    @PutMapping("/{id}")
    public Chicken updateChicken(@PathVariable Long id, @RequestBody Chicken chickenDetail) {
        return chickenService.updateChicken(id, chickenDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChicken(@PathVariable Long id){
        return chickenService.deleteChicken(id);
    }
}
