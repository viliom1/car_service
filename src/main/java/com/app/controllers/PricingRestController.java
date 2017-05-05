package com.app.controllers;

import com.app.models.binding.ServiceBinding;
import com.app.models.view.ServiceViewModel;
import com.app.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vilimir on 30.04.17.
 */
@RestController
public class PricingRestController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/get-pricing")
    public ResponseEntity<List<ServiceViewModel>> getPricing() {
        ResponseEntity<List<ServiceViewModel>> entity =
                new ResponseEntity<>(this.servicesService.getAllServices(), HttpStatus.OK);
        return entity;
    }

    @PostMapping("new-service")
    public ResponseEntity<ServiceViewModel> addNewService(@RequestBody ServiceBinding serviceBinding) {
        ServiceViewModel serviceViewModel = this.servicesService.addService(serviceBinding);
        return new ResponseEntity<ServiceViewModel>(serviceViewModel, HttpStatus.OK);
    }

    @DeleteMapping("/delete-service")
    public ResponseEntity deleteService(@RequestBody String name) {
        System.out.println(name);
        this.servicesService.deleteServiceByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
