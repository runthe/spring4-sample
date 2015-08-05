package com.soo.controller;

import com.soo.domain.User;
import com.soo.dto.UserDTO;
import com.soo.exception.ErrorResponse;
import com.soo.hateoas.UserAssembler;
import com.soo.repository.UserRepository;
import com.soo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author KHS
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserAssembler userAssembler;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postUser(@RequestBody UserDTO.Request request, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("똑같은 부분 수정230");
            System.out.println("zzzddd");
            System.out.println("hasErrors" + result.getAllErrors());
            return new ResponseEntity<>(new ErrorResponse("user.create.error", "뭔가 잘못 입력하신듯..."), HttpStatus.BAD_REQUEST);
        }

        User user = modelMapper.map(request, User.class);
        User newUser = userService.createUser(user);
        UserDTO.Response response = modelMapper.map(newUser, UserDTO.Response.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        List<UserDTO.Response> response = userRepository.findAll().stream().map(a -> modelMapper.map(a, UserDTO.Response.class))
            .collect(Collectors.toList());

    return new ResponseEntity<>(userAssembler.toResource(response), HttpStatus.OK);
}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable int id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(new ErrorResponse("user.not.found", "그런 유저는 없어.."), HttpStatus.BAD_REQUEST);
        }

        UserDTO.Response response = modelMapper.map(user, UserDTO.Response.class);
        response.setNumber(user.getNumber());

        response.add(linkTo(methodOn(UserController.class).getUsers()).withRel("all"));
        response.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity putUser(@PathVariable int id, @RequestBody @Valid UserDTO.Update request, BindingResult result) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(new ErrorResponse("user.not.found", "그런 유저는 없어.."), HttpStatus.BAD_REQUEST);
        }

        if (result.hasErrors()) {
            return new ResponseEntity<>(new ErrorResponse("user.update.error", "뭔가 잘못 입력하신듯.."), HttpStatus.BAD_REQUEST);
        }

        User userUpdate = modelMapper.map(request, User.class);
        userUpdate.setNumber(id);
        User updatedUser = userService.updateUser(userUpdate);

        return new ResponseEntity<>(modelMapper.map(updatedUser, UserDTO.Response.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable int id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(new ErrorResponse("user.not.found", "그런 유저는 없어.."), HttpStatus.BAD_REQUEST);
        }

        userRepository.delete(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
