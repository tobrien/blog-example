package com.discursive.blog.web;

import com.discursive.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepo;

    @RequestMapping("/{id}")
    public String readPost(@PathVariable Long id, Model model) {

        model.addAttribute( "message", "Hello World!" );
        return "helloWorld " + id.toString();
    }

    @RequestMapping("/new")
    public String newPost(Model model) {
        return "newPost";
    }


    @RequestMapping("/test")
    public ModelAndView test(Model model) {
        model.addAttribute("pageTitle", "Jade Title");
        model.addAttribute("youAreUsingJade", true);
        return new ModelAndView( "layout:posts/test", model.asMap() );
    }

}
