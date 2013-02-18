package com.discursive.blog.repository;

import com.discursive.blog.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
