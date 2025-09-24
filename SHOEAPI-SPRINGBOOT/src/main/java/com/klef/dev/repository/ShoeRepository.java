package com.klef.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.dev.entity.Shoe;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Integer> {
    // No extra methods needed for ShoeManager.jsx
    // Default JpaRepository gives us:
    // save(), findAll(), findById(), deleteById()
}
