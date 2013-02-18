package com.discursive.blog;

import com.discursive.blog.model.Post;
import com.discursive.blog.model.User;
import com.discursive.blog.repository.PostRepository;
import com.discursive.blog.repository.UserRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {

        // open/read the application context file
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");

        // instantiate our spring dao object from the application context
        UserRepository userRepo = (UserRepository) ctx.getBean("userRepository");
        PostRepository postRepo = (PostRepository) ctx.getBean("postRepository");

        User testUser = new User();
        userRepo.save( testUser );

        Post testPost = new Post();
        testPost.setUser( testUser );
        testPost.setTitle( "The Importance of User Testing" );
        testPost.setBody( "Testing is super important." );

        postRepo.save( testPost );


        System.out.println( "Test User: " + testUser.getId() );


    }

}
