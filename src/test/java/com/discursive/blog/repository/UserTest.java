package com.discursive.blog.repository;

import static org.junit.Assert.*;

import com.discursive.blog.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
@Transactional
public class UserTest  {

    @Autowired
    UserRepository userRepo;
    User user;

    @Before
    public void setUp() throws InvalidKeySpecException, NoSuchAlgorithmException {

        user = new User();
        user.setUsername( "tobrien" );
        user.setFirstName( "Tim" );
        user.setLastName( "O'Brien" );
        user.setEmail( "tobrien@discursive.com" );
        user.encryptPass( "Testing" );

    }

    @Test
    public void testSave() throws InvalidKeySpecException, NoSuchAlgorithmException {

        user = userRepo.save( user );

        assertEquals( user, userRepo.findOne( user.getId() ) );
        assertTrue( user.authPass( "Testing" ) );

    }

}
