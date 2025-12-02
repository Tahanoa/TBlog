package org.example.tblog.controller;

import jakarta.validation.Valid;
import org.example.tblog.dto.RegisterDto;
import org.example.tblog.dto.UserDto;
import org.example.tblog.model.Role;
import org.example.tblog.model.User;
import org.example.tblog.service.PostServiceImpl;
import org.example.tblog.service.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final PostServiceImpl postServiceImpl;


    public UserController(UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder, PostServiceImpl postServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.postServiceImpl = postServiceImpl;
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto) {
        if(!registerDto.password().equals(registerDto.rePassword())) {
            return "redirect:/register?error=true";
        }
        User user = new User();
        user.setUsername(registerDto.username());
        user.setFullName(registerDto.fullName());
        user.setEmail(registerDto.email());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setRole(Role.USER);

        userServiceImpl.save(user);

        return "login";
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        Page<User> users = userServiceImpl.findAll(pageable);
        Page<UserDto> result = users.map(u -> new UserDto(u.getId(), u.getUsername(), u.getRole()));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUserRole(@PathVariable int id, @RequestParam Role role) {
        User user = userServiceImpl.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));

        user.setRole(role);
        User updated = userServiceImpl.save(user);

        return ResponseEntity.ok(updated);
    }

}
