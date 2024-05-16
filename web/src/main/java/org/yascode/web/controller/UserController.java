package org.yascode.web.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yascode.persistence.entity.User;
import org.yascode.persistence.repository.UserRepository;
import org.yascode.service.business.UserService;

import java.time.LocalDate;
import java.util.Arrays;
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
    public ResponseEntity<List<User>> allUsers() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = {"/{id}", ""})
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Optional<Long> id) {
        return new ResponseEntity<>(userService.userById(id.orElse(null)), HttpStatus.OK);
    }


    @GetMapping(value = {"/{start}/{end}"})
    public ResponseEntity<List<User>> usersRegisteredBetween(@PathVariable(name = "start") String start,
                                                             @PathVariable(name = "end") String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return null;
    }

    //@PostConstruct
    void init() {

        User user1 = User.builder()
                .firstName("Yassin")
                .lastName("MELLOUKI")
                .email("yassin.mellouki@gmail.com")
                .creationDate(LocalDate.now())
                .build();

        User user2 = User.builder()
                .firstName("Akram")
                .lastName("Magri")
                .email("akram.magri@gmail.com")
                .creationDate(LocalDate.now())
                .build();

        userRepository.saveAll(Arrays.asList(user1, user2));

        User user = userService.userById(1L);

        int a = 120;
    }

}
