package com.app.repositories;

import com.app.enteties.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vilimir on 29.04.17.
 */
public interface RepairRepository extends JpaRepository<Repair,Long> {

    @Query(value = "SELECT r FROM Repair as r where r.user.username = :user")
    List<Repair> getAllRepairsOfUser(@Param("user") String user);

    @Query(value = "SELECT r FROM Repair as r where r.mechanic.username = :user and r.voteState = -1")
    List<Repair> getAllRepairsOfMechanic(@Param("user") String mechanic);
}
