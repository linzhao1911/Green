package com.beshton.audio.services;

import com.beshton.audio.entities.*;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface AudioService {
    Collection<Audio> getEmployees();
    Audio addEmployee(Audio employee);
    Audio getEmployee(String id);
}