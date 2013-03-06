package com.discursive.blog.web;

import com.discursive.blog.model.User;
import com.discursive.blog.repository.PostRepository;
import com.discursive.blog.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/users")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    UserRepository userRepo;

    @RequestMapping(value="/sign-up", method = RequestMethod.GET)
    public ModelAndView signup(Model model) {
        return new ModelAndView( "layout:users/sign-up", model.asMap() );
    }

    @RequestMapping(value="/sign-up", method = RequestMethod.POST)
    public ModelAndView processSignup(@ModelAttribute("user") User user, ServletRequest request, Model model) {

        String password = request.getParameter("password");

        try {
            user.encryptPass( password );
        } catch (Exception e) {
            log.error( "Error encrypting password.");
        }

        userRepo.save( user );

        model.addAttribute( "user", user );

        return new ModelAndView( "layout:users/profile", model.asMap() );
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ModelAndView listAll(Model model) {
        Iterable<User> users = userRepo.findAll();

        model.addAttribute("users",  users );
        return new ModelAndView("layout:users/all", model.asMap());
    }

    @RequestMapping(value="/profile/{id}", method = RequestMethod.GET)
    public ModelAndView listAll(@PathVariable("id") Long id, Model model) {
        User user = userRepo.findOne( id );
        model.addAttribute("user", user);
        return new ModelAndView("layout:users/profile", model.asMap());
    }


}
