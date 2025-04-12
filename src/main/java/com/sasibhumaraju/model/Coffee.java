package com.sasibhumaraju.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Float price;

    @Column(nullable = false)
    String description;

    @OneToMany( mappedBy = "coffee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<CoffeeOrder> coffeeOrders;

    public List<CoffeeOrder> getOrders() {
        return coffeeOrders;
    }

    public void setOrders(List<CoffeeOrder> coffeeOrders) {
        this.coffeeOrders = coffeeOrders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", order=" + coffeeOrders +
                '}';
    }
}
