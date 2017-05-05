package com.app.enteties;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vilimir on 03.04.17.
 */
@Entity
@Table(name = "services")
public class ServiceEnt {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private double price;

    public ServiceEnt() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
