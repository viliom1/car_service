package com.app.models.binding;

/**
 * Created by vilimir on 30.04.17.
 */
public class ServiceBinding {

    private String name;
    private Double price;

    public ServiceBinding() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
