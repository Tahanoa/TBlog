package org.example.tblog.controller;

import org.example.tblog.model.Post;
import org.example.tblog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
    private final PasswordEncoder passwordEncoder;
    private final PostService postService;
    public PageController(PasswordEncoder passwordEncoder, PostService postService) {
        this.passwordEncoder = passwordEncoder;
        this.postService = postService;


    }

    @GetMapping()
    public String index(Model model, Pageable pageable) {
        Page<Post> posts = postService.findAll(pageable);
        model.addAttribute("posts", posts);
        return "index";
    }


    @GetMapping("/auth/login")
    public String login() {
        System.out.println(passwordEncoder.encode("123456"));
        return "login";
    }

    @GetMapping("/auth/register")
    public String register() {
        return "register";
    }

    @GetMapping("/admin/posts")
    public String adminPosts(Model model, Pageable pageable) {
        Page<Post> page = postService.findAll(pageable);
        model.addAttribute("posts", page != null ? page : Page.empty());
        return "admin/posts";
    }
    @GetMapping("/admin/categories-tags")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminCategoriesTags() {
        return "admin/categories-tags";
    }

    @GetMapping("/admin/comments")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminComments() {
        return "admin/comments";
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminUsers() {
        return "admin/users";
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "admin/dashboard";
    }

}
