package com.sasibhumaraju.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Role role;

    @OneToMany( mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", orders=" + coffeeOrders +
                '}';
    }
}
