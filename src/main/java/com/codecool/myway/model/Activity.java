package com.codecool.myway.model;

import org.springframework.stereotype.Component;

@Component
public class Activity {
    private String description;
    private Double price;

    public Activity(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public Activity() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
