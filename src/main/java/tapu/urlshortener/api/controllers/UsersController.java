package tapu.urlshortener.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tapu.urlshortener.business.abstracts.UserService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.LoginRequest;
import tapu.urlshortener.entities.dtos.UserCreateRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return ResponseEntity.ok(
                this.userService.save(user));
    }
}