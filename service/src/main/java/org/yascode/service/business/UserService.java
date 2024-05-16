package org.yascode.service.business;

import org.yascode.persistence.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> allUsers();
    User userById(Long id);

    List<User> usersRegisteredBetween(LocalDate startDate, LocalDate endDate);

    List<User> usersRegisteredBetween(Optional<String> startDate, Optional<String> endDate);
}
