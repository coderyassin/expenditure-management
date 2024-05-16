package org.yascode.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yascode.persistence.entity.User;
import org.yascode.persistence.repository.UserRepository;
import org.yascode.service.business.UserService;
import org.yascode.shared.dto.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = {"/all"})
    public ResponseEntity<List<UserDto>> allUsers() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = {"/{id}", ""})
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Optional<Long> id) {
        return new ResponseEntity<>(userService.userById(id.orElse(null)), HttpStatus.OK);
    }


    @GetMapping(value = {"/{start}/{end}"})
    public ResponseEntity<List<User>> usersRegisteredBetween(@PathVariable(name = "start") String start,
                                                             @PathVariable(name = "end") String end) {
        return new ResponseEntity<>(userService.usersRegisteredBetween(LocalDate.parse(start), LocalDate.parse(end)), HttpStatus.OK);
    }

    @GetMapping(value = {"/usersRegisteredBetween"})
    public ResponseEntity<List<User>> usersRegisteredBetween(@RequestParam(name = "start", required = false) Optional<String> start,
                                                             @RequestParam(name = "end", required = false) Optional<String> end) {
        return new ResponseEntity<>(userService.usersRegisteredBetween(start, end), HttpStatus.OK);
    }

}
