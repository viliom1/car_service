package com.app.models.view;

/**
 * Created by vilimir on 30.04.17.
 */
public class ServiceViewModel {
    private String name;
    private String price;

    public ServiceViewModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
