package com.app.services;

import com.app.enteties.*;
import com.app.models.binding.*;
import com.app.models.view.RepairViewModel;
import com.app.models.view.RepairViewModelForMechanic;
import com.app.models.view.UserViewModel;
import com.app.repositories.RepairRepository;
import com.app.repositories.RoleRepository;
import com.app.repositories.UserRepository;
import com.app.utils.Constants;
import com.app.utils.Mail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vilimir on 03.04.17.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RepairRepository repairRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       RepairRepository repairRepository, ModelMapper modelMapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.repairRepository = repairRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void registerUser(RegisterBinding registerBinding){
        Client user = modelMapper.map(registerBinding,Client.class);
        user.setPassword(bCryptPasswordEncoder.encode(registerBinding.getPassword()));
        user.setAccountNonExpired(true);
        user.addRole(roleRepository.findByName(Constants.ROLE_CLIENT));
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        userRepository.save(user);
        Mail.sendFromGMail(user.getUsername(),"Your registration is complete!",String.format("Hello,%n Your registration at CarServiceApp is done, you can log into your account now."));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(s);
        System.out.println(user.getFirstName());
        if (user == null || user.getAuthorities().size() == 0){
            throw new UsernameNotFoundException("Invalid credentials");
        }
        return user;
    }

    public UserViewModel getViewModelByUsername(String username){
        User user = this.userRepository.findOneByUsername(username);
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setEmail(user.getUsername());
        userViewModel.setFirstName(user.getFirstName());
        userViewModel.setLastName(user.getLastName());
        userViewModel.setEncodedPassword(user.getPassword());
        return userViewModel;
    }
    @Transactional
    public boolean changePassword(String username, ChangePasswordBinding changePasswordBinding){
        User user = this.userRepository.findOneByUsername(username);
        if (!bCryptPasswordEncoder.matches(changePasswordBinding.getOldPassword(),user.getPassword())){
            return false;
        }
        if (changePasswordBinding.getNewPassword().equals(changePasswordBinding.getConfirmNewPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(changePasswordBinding.getNewPassword()));
            return true;
        }
        return false;
    }


    public List<RepairViewModel> getRepairs(String username){
        List<Repair> repairs =  this.repairRepository.getAllRepairsOfUser(username);
        List<RepairViewModel> views = new ArrayList<>();
        if (repairs.size() == 0){
            return views;
        }
        for (int i = 0; i < repairs.size(); i++) {
            Repair  r = repairs.get(i);
            RepairViewModel rvm = new RepairViewModel();
            rvm.setCarManufacturer(r.getCarManufacturer());
            rvm.setCarModel(r.getCarModel());
            rvm.setDescription(r.getDescription());
            rvm.setVoteState(r.getVoteState());
            rvm.setId(r.getId());
            rvm.setMechanicName(String.format("%s %s",r.getMechanic().getFirstName(), r.getMechanic().getLastName()));
            views.add(rvm);
        }
        return views;
    }

    public void registerAdmin(RegisterBinding registerBinding){
        Admin admin = new Admin();
        admin.setUsername(registerBinding.getUsername());
        admin.setFirstName(registerBinding.getFirstName());
        admin.setLastName(registerBinding.getLastName());
        admin.setPassword(this.bCryptPasswordEncoder.encode(registerBinding.getPassword()));
        admin.setEnabled(true);
        admin.setAccountNonLocked(true);
        admin.setCredentialsNonExpired(true);
        admin.setAccountNonExpired(true);
        admin.addRole(this.roleRepository.findByName(Constants.ROLE_ADMIN));
        this.userRepository.save(admin);
    }

    public void registerMechanic(RegisterBinding registerBinding) {
        Mechanic mechanic = new Mechanic();
        mechanic.setUsername(registerBinding.getUsername());
        mechanic.setFirstName(registerBinding.getFirstName());
        mechanic.setLastName(registerBinding.getLastName());
        mechanic.setPassword(this.bCryptPasswordEncoder.encode(registerBinding.getPassword()));
        mechanic.setEnabled(true);
        mechanic.setAccountNonLocked(true);
        mechanic.setCredentialsNonExpired(true);
        mechanic.setAccountNonExpired(true);
        mechanic.addRole(this.roleRepository.findByName(Constants.ROLE_MECHANIC));
        this.userRepository.save(mechanic);
    }

    public void addNewRepair(RepairBinding repairBidning, String mechanicUserName) {
        Mechanic mechanic = (Mechanic) this.userRepository.findOneByUsername(mechanicUserName);
        User client = this.userRepository.findOneByUsername(repairBidning.getClientEmail());

        Repair repair = new Repair();
        repair.setMechanic(mechanic);
        repair.setUser(client);
        repair.setCarManufacturer(repairBidning.getCarManufacturer());
        repair.setCarModel(repairBidning.getCarModel());
        repair.setDescription(repairBidning.getDescription());
        repair.setVoteState(Constants.REPAIR_IN_PROCESS);
        this.repairRepository.save(repair);
    }

    public boolean isUserExisting(String username){
        User user = this.userRepository.findOneByUsername(username);
        if (user != null){
            return true;
        }
        return false;
    }

    public List<RepairViewModelForMechanic> getMechanicRepairs(String mechanicUsername){
        List<RepairViewModelForMechanic> repairsView = new ArrayList<>();
        List<Repair> repairs = this.repairRepository.getAllRepairsOfMechanic(mechanicUsername);

        for (int i = 0; i < repairs.size(); i++) {
            RepairViewModelForMechanic repairViewModel = new RepairViewModelForMechanic();
            repairViewModel.setDescription(repairs.get(i).getDescription());
            repairViewModel.setCarModel(repairs.get(i).getCarModel());
            repairViewModel.setCarManufacturer(repairs.get(i).getCarManufacturer());
            repairViewModel.setClient(repairs.get(i).getUser().getUsername());
            repairViewModel.setStatus(repairs.get(i).getVoteState());
            repairViewModel.setId(repairs.get(i).getId());
            repairsView.add(repairViewModel);
        }
        return repairsView;
    }

    @Transactional
    public void updateRepair(UpdateRepairBinding repairBinding) {
        Repair repair = this.repairRepository.findOne(repairBinding.getId());
        if (repairBinding.isDone()){
            repair.setVoteState(Constants.REPAIR_NO_VOTE);
        }
        String desc = repair.getDescription();
        desc = String.format("%s %n%s",desc,repairBinding.getDescription());
        repair.setDescription(desc);
    }

    @Transactional
    public void updateUserVote(UserVoteBind userVoteBind) {
        Repair repair = this.repairRepository.findOne(userVoteBind.getId());
        if (userVoteBind.isVote()){
            repair.getMechanic().setUpVotes(repair.getMechanic().getUpVotes()+1);
            repair.setVoteState(Constants.REPAIR_UP_VOTE);
        }else{
            repair.getMechanic().setDownVotes(repair.getMechanic().getDownVotes()+1);
            repair.setVoteState(Constants.REPAIR_DOWN_VOTE);
        }
    }
}
