package com.app.controllers;

import com.app.models.binding.RegisterBinding;
import com.app.models.binding.RepairBinding;
import com.app.models.binding.UpdateRepairBinding;
import com.app.models.binding.UserVoteBind;
import com.app.models.view.RepairViewModel;
import com.app.models.view.RepairViewModelForMechanic;
import com.app.models.view.UserViewModel;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by vilimir on 26.04.17.
 */
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/getviewmodel")
    public ResponseEntity<UserViewModel> getUserModel(Principal principal) {
        ResponseEntity<UserViewModel> responseEntity  =
                new ResponseEntity(this.userService.getViewModelByUsername(principal.getName()), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/user/getrepairs")
    public ResponseEntity<List<RepairViewModel>> getUserRepairs(Principal principal){
        ResponseEntity<List<RepairViewModel>> repairs
                = new ResponseEntity<List<RepairViewModel>>(this.userService.getRepairs(principal.getName()),HttpStatus.OK);
        return repairs;
    }

    @PostMapping("/admin/add-admin")
    public ResponseEntity addAdmin(@RequestBody RegisterBinding registerBinding){

        this.userService.registerAdmin(registerBinding);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/add-mechanic")
    public ResponseEntity addMechanic(@RequestBody RegisterBinding registerBinding){
        this.userService.registerMechanic(registerBinding);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/mechanic/add-repair")
    public ResponseEntity addRepair(@RequestBody RepairBinding repairBidning, Principal principal){

        System.out.println("kuro mi se nasra");
        this.userService.addNewRepair(repairBidning,principal.getName());
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/mechanic/check-user")
    public ResponseEntity checkIfUserExists(@RequestBody String username){

        boolean userExists = this.userService.isUserExisting(username);
        if (userExists){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);

    }

    @GetMapping("/mechanic/get-repairs")
    public ResponseEntity<List<RepairViewModelForMechanic>> getMechanicRepairs(Principal principal){
        List<RepairViewModelForMechanic> repairs = this.userService.getMechanicRepairs(principal.getName());
        ResponseEntity<List<RepairViewModelForMechanic>> responseEntity = new ResponseEntity<List<RepairViewModelForMechanic>>(repairs,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/mechanic/update-repair")
    public ResponseEntity updateRepair(@RequestBody UpdateRepairBinding repairBinding){
        this.userService.updateRepair(repairBinding);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/user/update-repair")
    public ResponseEntity updateUserVote(@RequestBody UserVoteBind userVoteBind){
        this.userService.updateUserVote(userVoteBind);
        return new ResponseEntity(HttpStatus.OK);
    }
}
