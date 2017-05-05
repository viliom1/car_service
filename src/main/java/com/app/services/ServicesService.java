package com.app.services;

import com.app.enteties.ServiceEnt;
import com.app.models.binding.ServiceBinding;
import com.app.models.view.ServiceViewModel;
import com.app.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vilimir on 30.04.17.
 */
@Service
public class ServicesService {
    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceViewModel> getAllServices() {
        List<ServiceEnt> services = serviceRepository.findAll();
        List<ServiceViewModel> views = new ArrayList<>();
        for (int i = 0; i < services.size(); i++) {
            ServiceViewModel serviceViewModel = new ServiceViewModel();
            serviceViewModel.setName(services.get(i).getName());
            serviceViewModel.setPrice(String.valueOf(services.get(i).getPrice()));
            views.add(serviceViewModel);
        }
        return views;
    }

    public ServiceViewModel addService(ServiceBinding serviceBinding){
        ServiceEnt serviceEnt = new ServiceEnt();
        serviceEnt.setName(serviceBinding.getName());
        serviceEnt.setPrice(serviceBinding.getPrice());
        this.serviceRepository.save(serviceEnt);

        ServiceViewModel serviceViewModel = new ServiceViewModel();
        serviceViewModel.setPrice(String.valueOf(serviceBinding.getPrice()));
        serviceViewModel.setName(serviceBinding.getName());
        return serviceViewModel;
    }

    @Transactional
    public void deleteServiceByName(String name) {
        this.serviceRepository.deleteByName(name);
    }
}
