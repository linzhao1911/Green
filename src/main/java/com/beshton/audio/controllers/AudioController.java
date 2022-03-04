package com.beshton.audio.controllers;

import com.beshton.audio.entities.*;
import com.beshton.audio.exceptions.*;
import com.beshton.audio.repos.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.*;

@RestController
public class AudioController {

    private final AudioRepository repository;

    AudioController(AudioRepository repository) {
        this.repository = repository;
    }



    @GetMapping("/employees")
    CollectionModel<EntityModel<Audio>> all() {

        List<EntityModel<Audio>> employees = repository.findAll().stream()
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(AudioController.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(AudioController.class).all()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(AudioController.class).all()).withSelfRel());
    }


    @PostMapping("/employees")
    Audio newEmployee(@RequestBody Audio newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

//    @GetMapping("/employees/{id}")
//    Employee one(@PathVariable Long id) {
//
//        return repository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//    }

    @GetMapping("/employees/{id}")
    EntityModel<Audio> one(@PathVariable Long id) {

        Audio employee = repository.findById(id) //
                .orElseThrow(() -> new AudioNotFoundException(id));

        return EntityModel.of(employee, //
                linkTo(methodOn(AudioController.class).one(id)).withSelfRel(),
                linkTo(methodOn(AudioController.class).all()).withRel("employees"));
    }

    @PutMapping("/employees/{id}")
    Audio replaceEmployee(@RequestBody Audio newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}