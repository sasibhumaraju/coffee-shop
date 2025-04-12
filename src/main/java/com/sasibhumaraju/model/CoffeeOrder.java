package com.sasibhumaraju.model;

import jakarta.persistence.*;

@Entity
public class CoffeeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn (name = "coffee_id")
    Coffee coffee;

    @ManyToOne
    @JoinColumn (name = "user_id")
    AppUser appUser;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", coffee=" + coffee +
                ", user=" + appUser +
                ", status=" + status +
                '}';
    }
}
