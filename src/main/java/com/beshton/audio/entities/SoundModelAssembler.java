package com.beshton.audio.entities;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.beshton.audio.controllers.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SoundModelAssembler implements RepresentationModelAssembler<Sound, EntityModel<Sound>> {

    @Override
    public EntityModel<Sound> toModel(Sound order) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<Sound> orderModel = EntityModel.of(order,
                linkTo(methodOn(SoundController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(SoundController.class).all()).withRel("orders"));

        // Conditional links based on state of the order

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(SoundController.class).cancel(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(SoundController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }
}