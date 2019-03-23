package com.rest.webservices.restfulwebservices.post;

import com.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Post, Integer> { //Entity, ID data type
}
