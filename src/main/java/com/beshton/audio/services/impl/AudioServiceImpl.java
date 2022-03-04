package com.beshton.audio.services.impl;

import com.beshton.audio.entities.*;
import com.beshton.audio.services.*;
import com.beshton.audio.repos.*;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AudioServiceImpl implements AudioService {

    private AudioRepository EmployeeRepository;
    public void setEmployeeRepository(AudioRepository repo) {
        this.EmployeeRepository = repo;
    }
    // constructors, other override methods
    @Override
    public Audio getEmployee(String id) {
        Audio emp = null;
        try {
//            emp = EmployeeRepository.findById(emp.getId());
        } catch (Exception ex) {
            emp = null;
        }
        return emp;
    }

    @Override
    public Collection<Audio>  getEmployees() {
        Audio emp = null;
        try {
//            emp = EmployeeRepository.all();
        } catch (Exception ex) {
            emp = null;
        }
        return new ArrayList<Audio>();
    }
    @Override
    public Audio addEmployee(Audio emp) {
//        final Employee existingEmployee = EmployeeRepository.findById(emp.getId());
        Audio existingEmployee = null;
        if (existingEmployee == null) {
//            throw new Exception("Employee is null");
        }

        return existingEmployee;
    }

    // conversion logic
}