package com.discursive.blog.repository;

import com.discursive.blog.model.Post;
import com.discursive.blog.model.User;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends CrudRepository<Post, Long> {


}
