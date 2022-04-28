package com.test.springbootapp.repository;

import com.test.springbootapp.entity.Chicken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChickenRepository extends JpaRepository <Chicken, Long> {
    Optional<Chicken> findByBrand(String brand);
}
