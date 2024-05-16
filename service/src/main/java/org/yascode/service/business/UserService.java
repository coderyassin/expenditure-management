package org.yascode.service.business;

import org.yascode.persistence.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<User> allUsers();
    User userById(Long id);

    List<User> usersRegisteredBetween(LocalDate startDate, LocalDate endDate);
}
