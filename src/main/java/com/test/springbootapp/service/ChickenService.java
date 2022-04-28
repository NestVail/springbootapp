package com.test.springbootapp.service;

import com.test.springbootapp.entity.Chicken;
import com.test.springbootapp.exception.ResourceNotFoundException;
import com.test.springbootapp.repository.ChickenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChickenService {
    @Autowired
    private ChickenRepository chickenRepository;

    public Chicken insertChicken(Chicken chicken) {
        return chickenRepository.save(chicken);
    }

    @Transactional(readOnly = true)
    public List<Chicken> selectAllChicken() {
        return chickenRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Chicken selectChicken(Long id) {
        Optional<Chicken> optionalChicken = chickenRepository.findById(id);
        Chicken existChicken = optionalChicken.orElseThrow(() -> new ResourceNotFoundException("Chicken", "id", id));
        return existChicken;
    }

    public Chicken updateChicken(Long id, Chicken chickenDetail) {
        Chicken existChicken = chickenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chicken", "id", id));
        existChicken.setName(chickenDetail.getName());
        existChicken.setBrand(chickenDetail.getBrand());
        existChicken.setPrice(chickenDetail.getPrice());
        return existChicken;
    }

    public ResponseEntity<?> deleteChicken(Long id) {
        Optional<Chicken> optionalChicken = chickenRepository.findById(id);
        if (!optionalChicken.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " Chicken Not Found");
        }
        Chicken existChicken = optionalChicken.get();
        chickenRepository.delete(existChicken);
        return ResponseEntity.ok("Chicken Delete Complete");
    }
}
