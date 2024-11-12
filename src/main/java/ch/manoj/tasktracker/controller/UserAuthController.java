package ch.manoj.tasktracker.controller;

import ch.manoj.tasktracker.dto.UsersDto;
import ch.manoj.tasktracker.entity.Users;
import ch.manoj.tasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto){
        return new ResponseEntity<>(userService.createUser(usersDto), HttpStatus.CREATED);

    }
}
