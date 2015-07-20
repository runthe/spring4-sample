package com.soo.hateoas;

import com.soo.controller.UserController;
import com.soo.dto.UserDTO;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author KHS
 */
@Component
public class UserAssembler implements ResourceAssembler<List<UserDTO.Response>, Resources<Resource<UserDTO.Response>>> {
    @Override
    public Resources<Resource<UserDTO.Response>> toResource(List<UserDTO.Response> userList) {
        List<Resource<UserDTO.Response>> toList = userList.stream().map(postDto -> toResource(postDto)).collect(Collectors.toList());
        Resources<Resource<UserDTO.Response>> resources = new Resources<>(toList);

        return resources;
    }

    private Resource<UserDTO.Response> toResource(UserDTO.Response userDTO) {
        Resource<UserDTO.Response> resource = new Resource<>(userDTO);
        resource.add(ControllerLinkBuilder.linkTo(methodOn(UserController.class).getUsers()).withRel("all"));
        resource.add(linkTo(methodOn(UserController.class).getUser(userDTO.getNumber())).withSelfRel());

        return resource;
    }
}
