package com.discursive.blog.web;

import com.discursive.blog.repository.PostRepository;
import com.discursive.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepo;

    @RequestMapping(value="/sign-up", method = RequestMethod.GET)
    public ModelAndView signup(Model model) {
        return new ModelAndView( "layout:users/sign-up", model.asMap() );
    }

    @RequestMapping(value="/sign-up", method = RequestMethod.POST)
    public ModelAndView processSignup(Model model) {
        return new ModelAndView( "layout:users/sign-up", model.asMap() );
    }



}
