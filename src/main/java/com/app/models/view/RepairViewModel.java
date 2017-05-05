package com.app.models.view;

/**
 * Created by vilimir on 29.04.17.
 */
public class RepairViewModel {
    private String carManufacturer;
    private String carModel;
    private String description;
    private int voteState;
    private String mechanicName;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVoteState() {
        return voteState;
    }

    public void setVoteState(int voteState) {
        this.voteState = voteState;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }
}
