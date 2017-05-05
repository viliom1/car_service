package com.app.enteties;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vilimir on 05.04.17.
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
