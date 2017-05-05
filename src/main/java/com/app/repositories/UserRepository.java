package com.app.repositories;

import com.app.enteties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vilimir on 03.04.17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);


    User findOneByUsername(String username);

    }
