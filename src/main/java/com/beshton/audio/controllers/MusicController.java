package com.beshton.audio.controllers;

import com.beshton.audio.entities.*;
import com.beshton.audio.exceptions.MusicNotFoundException;
import com.beshton.audio.repos.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.*;

@RestController
public class MusicController {
    private final MusicRepository repository;

    MusicController(MusicRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/sales")
    CollectionModel<EntityModel<Music>> all() {

        List<EntityModel<Music>> sales = repository.findAll().stream()
                .map(sale -> EntityModel.of(sale,
                        linkTo(methodOn(MusicController.class).one(sale.getId())).withSelfRel(),
                        linkTo(methodOn(MusicController.class).all()).withRel("sales")))
                .collect(Collectors.toList());

        return CollectionModel.of(sales, linkTo(methodOn(MusicController.class).all()).withSelfRel());
    }

    @PostMapping("/sales")
    Music newSale(@RequestBody Music newSale) {
        return repository.save(newSale);
    }

    @GetMapping("/sales/{id}")
    EntityModel<Music> one(@PathVariable Long id) {

        Music sale = repository.findById(id) //
                .orElseThrow(() -> new MusicNotFoundException(id));

        return EntityModel.of(sale, //
                linkTo(methodOn(MusicController.class).one(id)).withSelfRel(),
                linkTo(methodOn(MusicController.class).all()).withRel("sales"));
    }

    @PutMapping("/sales/{id}")
    Music replaceSale(@RequestBody Music newSale, @PathVariable Long id) {

        return repository.findById(id)
                .map(sale -> {
                    sale.setItemName(newSale.getItemName());
                    sale.setSellerFirstName(newSale.getSellerFirstName());
                    sale.setSellerLastName(newSale.getSellerLastName());
                    sale.setCategory(newSale.getCategory());
                    sale.setPrice(newSale.getPrice());
                    sale.setPostalCode(newSale.getPostalCode());
                    sale.setManufacturer(newSale.getManufacturer());
                    sale.setModelName(newSale.getModelName());
                    sale.setUsed(newSale.getUsed());
                    sale.setDescription(newSale.getDescription());
                    sale.setSaleStatus(newSale.getSaleStatus());
                    sale.setTimeStamp(newSale.getTimeStamp());
                    sale.setLatitude(newSale.getLatitude());
                    sale.setLongitude(newSale.getLongitude());
                    return repository.save(sale);
                })
                .orElseGet(() -> {
                    newSale.setId(id);
                    return repository.save(newSale);
                });
    }

    @DeleteMapping("/sales/{id}")
    void deleteSale(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


