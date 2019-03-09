package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
