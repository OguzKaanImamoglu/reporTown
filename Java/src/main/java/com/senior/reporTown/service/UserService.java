package com.senior.reporTown.service;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Citizen;
import com.senior.reporTown.model.Institution;
import com.senior.reporTown.model.Official;
import com.senior.reporTown.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public ApplicationUser signUpUser(ApplicationUser applicationUser) {
        logger.info(String.format("Signup Request By: %s", applicationUser.getUsername()));
        boolean userExists = userRepository
                .findByUsername(applicationUser.getUsername())
                .isPresent();

        if (userExists) {
            logger.error(String.format("Username %s already taken", applicationUser.getUsername()));
            throw new IllegalStateException("username already taken");
        }

        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);

        userRepository.save(applicationUser);
        logger.info(String.format("User %s is registered successfully", applicationUser.getUsername()));
        return applicationUser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public UserDetails findUserById(ObjectId id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));
    }

    public void changeVolunteer(ObjectId userId) {
        Citizen citizen = (Citizen) findUserById(userId);
        if(citizen.isVolunteer()==true){
            citizen.setVolunteer(false);
        }
        else{
            citizen.setVolunteer(true);
        }
        userRepository.save(citizen);
    }

    public void updateBio(ObjectId userId, String bio) {

        ApplicationUser user = (ApplicationUser) findUserById(userId);

        if((user.getRole().toString()).equals("CITIZEN")){
            Citizen citizen = (Citizen) user;
            citizen.setBio(bio);
            userRepository.save(citizen);
        }
        else if((user.getRole().toString()).equals("OFFICIAL")){
            Official official = (Official) user;

            userRepository.save(official);
        }
        else if((user.getRole().toString()).equals("INSTITUTION")){
            Institution institution = (Institution) user;
            institution.setBio(bio);
            userRepository.save(institution);
        }
    }
}
