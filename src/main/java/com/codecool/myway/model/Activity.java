package com.codecool.myway.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Activity {
    private String description;
    private Double price;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(description, activity.description) &&
                Objects.equals(price, activity.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price);
    }
}
