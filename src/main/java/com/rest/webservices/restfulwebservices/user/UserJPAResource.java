package com.rest.webservices.restfulwebservices.user;

import com.rest.webservices.restfulwebservices.post.Post;
import com.rest.webservices.restfulwebservices.post.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;


    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }


    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("id - " + id);
        }

        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }


    @PostMapping("/jpa/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody  User user) {
        User savedUser = userRepository.save(user);

        if(savedUser == null) {
            throw new UserNotFoundException(user.toString());
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("id - " + id);
        }

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost( @RequestBody Post post, @PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException(userOptional.toString());
        }

        User user = userOptional.get();
        post.setUser(user);
        postsRepository.save(post);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
