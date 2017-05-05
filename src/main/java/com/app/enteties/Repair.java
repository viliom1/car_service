package com.app.enteties;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vilimir on 03.04.17.
 */
@Entity
@Table(name = "repairs")
public class Repair {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private User user;
    private String carManufacturer;
    private String carModel;
    private String description;
    private int voteState;   //  -1 - repair in process 0 - no vote , 1 - upVote, 2 - downVote;

    public Repair() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}

