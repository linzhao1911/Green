package com.beshton.audio.controllers;

import com.beshton.audio.entities.*;
import com.beshton.audio.exceptions.*;
import com.beshton.audio.repos.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.*;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.problem.*;

import java.util.List;
import java.util.stream.*;

@RestController
public class SoundController {

  private final SoundRepository orderRepository;
  private final SoundModelAssembler assembler;

  SoundController(SoundRepository orderRepository, SoundModelAssembler assembler) {

    this.orderRepository = orderRepository;
    this.assembler = assembler;
  }

  @GetMapping("/orders")
  public CollectionModel<EntityModel<Sound>> all() {

    List<EntityModel<Sound>> orders = orderRepository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(orders, //
        linkTo(methodOn(SoundController.class).all()).withSelfRel());
  }

  @GetMapping("/orders/{id}")
  public EntityModel<Sound> one(@PathVariable Long id) {

    Sound order = orderRepository.findById(id) //
        .orElseThrow(() -> new SoundNotFoundException(id));

    return assembler.toModel(order);
  }

  @PostMapping("/orders")
  ResponseEntity<EntityModel<Sound>> newOrder(@RequestBody Sound order) {

    order.setStatus(Status.IN_PROGRESS);
    Sound newOrder = orderRepository.save(order);

    return ResponseEntity //
        .created(linkTo(methodOn(SoundController.class).one(newOrder.getId())).toUri()) //
        .body(assembler.toModel(newOrder));
  }

  @DeleteMapping("/orders/{id}/cancel")
  public ResponseEntity<?> cancel(@PathVariable Long id) {

    Sound order = orderRepository.findById(id) //
        .orElseThrow(() -> new SoundNotFoundException(id));

    if (order.getStatus() == Status.IN_PROGRESS) {
      order.setStatus(Status.CANCELLED);
      return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
    }

    return ResponseEntity //
        .status(HttpStatus.METHOD_NOT_ALLOWED) //
        .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
        .body(Problem.create() //
            .withTitle("Method not allowed") //
            .withDetail("You can't cancel an order that is in the " + order.getStatus() + " status"));
  }

  @PutMapping("/orders/{id}/complete")
  public ResponseEntity<?> complete(@PathVariable Long id) {

    Sound order = orderRepository.findById(id) //
        .orElseThrow(() -> new SoundNotFoundException(id));

    if (order.getStatus() == Status.IN_PROGRESS) {
      order.setStatus(Status.COMPLETED);
      return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
    }

    return ResponseEntity //
        .status(HttpStatus.METHOD_NOT_ALLOWED) //
        .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
        .body(Problem.create() //
            .withTitle("Method not allowed") //
            .withDetail("You can't complete an order that is in the " + order.getStatus() + " status"));
  }
}