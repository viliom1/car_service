package com.app.enteties;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.TABLE;

/**
 * Created by vilimir on 03.04.17.
 */
@Entity
@Table(name = "mechanic")
public class Mechanic extends User{
    private String description;
    private int upVotes;
    private int downVotes;

    public Mechanic() {


    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }
}
