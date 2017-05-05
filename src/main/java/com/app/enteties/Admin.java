package com.app.enteties;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by vilimir on 07.04.17.
 */
@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() {

    }
}
