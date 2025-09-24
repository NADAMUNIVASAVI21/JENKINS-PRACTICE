package com.klef.dev.service;

import java.util.List;
import com.klef.dev.entity.Shoe;

public interface ShoeService {
    Shoe addShoe(Shoe shoe);
    List<Shoe> getAllShoes();
    Shoe getShoeById(int id);
    Shoe updateShoe(Shoe shoe);
    void deleteShoeById(int id);
}
