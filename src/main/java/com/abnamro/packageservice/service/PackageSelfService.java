package com.abnamro.packageservice.service;


import com.abnamro.packageservice.model.Users;
import com.abnamro.packageservice.repository.PackageServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageSelfService {


    PackageServiceRepository packageServiceRepository;
    /**
     * Get All
     * @return the all
     */
    public List<Users> getAll() {
        return packageServiceRepository.findAll();
    }

}
