package com.abnamro.packageservice.handler;

import com.abnamro.packageservice.model.Users;
import com.abnamro.packageservice.service.PackageSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PackageServiceHandler {

    @Autowired
    PackageSelfService packageSelfService;


    /**
     * Retrieves a list of available users
     *
     * @return the all user details
     */
    public List<Users> getAllUsers() {
        return packageSelfService.getAll().stream().map(Users::allUsers ).collect(Collectors.toList());
    }
}
