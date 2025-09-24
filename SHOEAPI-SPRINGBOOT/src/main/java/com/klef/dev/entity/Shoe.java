package com.klef.dev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shoe_table")
public class Shoe {
  
    @Id
    @Column(name = "shoe_id")
    private int id;

    @Column(name = "shoe_brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "shoe_model", nullable = false, length = 50)
    private String model;

    @Column(name = "shoe_size", nullable = false, length = 10)
    private String size;

    @Column(name = "shoe_color", nullable = false, length = 30)
    private String color;

    @Column(name = "shoe_category", nullable = false, length = 20)
    private String category; // Sports, Casual, Formal

    @Column(name = "shoe_price", nullable = false)
    private double price;

    @Column(name = "shoe_stock", nullable = false)
    private int stock;

    @Column(name = "shoe_description", length = 255)
    private String description;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // toString
    @Override
    public String toString() {
        return "Shoe [id=" + id + ", brand=" + brand + ", model=" + model + ", size=" + size +
               ", color=" + color + ", category=" + category + ", price=" + price +
               ", stock=" + stock + ", description=" + description + "]";
    }
}
