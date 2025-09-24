package com.klef.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.dev.entity.Shoe;
import com.klef.dev.repository.ShoeRepository;

@Service
public class ShoeServiceImpl implements ShoeService {

    @Autowired
    private ShoeRepository shoeRepository;

    @Override
    public Shoe addShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }

    @Override
    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    @Override
    public Shoe getShoeById(int id) {
        Optional<Shoe> opt = shoeRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Shoe updateShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }

    @Override
    public void deleteShoeById(int id) {
        shoeRepository.deleteById(id);
    }
}
