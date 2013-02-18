package com.discursive.blog.repository;

import com.discursive.blog.model.Post;
import com.discursive.blog.model.Tag;
import com.discursive.blog.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
@Transactional
public class PostTest {

    @Autowired
    PostRepository postRepo;
    Post post;

    @Before
    public void setUp() {

        post = new Post();
        post.setTitle( "This is a test" );
        post.setBody( "This is the body of the post" );
        post.setPublishDate( new Date() );

        Set<Tag> tags = new HashSet<Tag>();

        Tag xml = new Tag();
        xml.setName( "xml" );

        tags.add( xml );

    }

    @Test
    public void testSave() {

        post = postRepo.save( post );

        assertEquals( post, postRepo.findOne( post.getId() ) );
    }

}
