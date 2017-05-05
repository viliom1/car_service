package com.app.repositories;

import com.app.enteties.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by vilimir on 09.04.17.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "SELECT r from Role as r where r.authority = :name")
    Role findByName(@Param("name") String name);
}
