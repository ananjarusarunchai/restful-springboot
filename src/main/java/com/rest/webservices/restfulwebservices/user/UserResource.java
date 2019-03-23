package com.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static  org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;


    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }


    @GetMapping(path = "/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id - " + id);
        }

        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }


    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody  User user) {
        User savedUser = userDaoService.saveUser(user);

        if(savedUser == null) {
            throw new UserNotFoundException(user.toString());
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User deletedUser = userDaoService.deleteById(id);

        if(deletedUser == null) {
            throw new UserNotFoundException("id - " + id);
        }
    }
}
