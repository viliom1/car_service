package com.app.repositories;

import com.app.enteties.ServiceEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by vilimir on 30.04.17.
 */
@Repository
public interface ServiceRepository extends JpaRepository<ServiceEnt,Long> {

    @Transactional
    Integer deleteByName(String name);
}
