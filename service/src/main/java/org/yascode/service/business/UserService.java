package org.yascode.service.business;

import org.yascode.shared.dto.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> allUsers();
    UserDto userById(Long id);

    List<UserDto> usersRegisteredBetween(LocalDate startDate, LocalDate endDate);

    List<UserDto> usersRegisteredBetween(Optional<String> startDate, Optional<String> endDate);
}
