package org.yascode.service.business.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.User;
import org.yascode.persistence.repository.UserRepository;
import org.yascode.persistence.repository.specification.UserSpec;
import org.yascode.service.business.UserService;
import org.yascode.shared.dto.UserDto;
import org.yascode.shared.mapper.UserMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger LOGGER = Logger.getLogger(getClass().getName());
    private LogRecord logRecord = new LogRecord(Level.INFO, null);
    private final UserRepository userRepository;
    private final UserMapping<User> UserDtoToUser;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapping<User> userDtoToUser) {
        this.userRepository = userRepository;
        UserDtoToUser = userDtoToUser;
    }

    @PostConstruct
    private void init() {
        logRecord.setLevel(Level.INFO);
        logRecord.setLoggerName(LOGGER.getName());
    }

    /**
     * @return all users
     */
    @Override
    public List<UserDto> allUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(user -> UserDtoToUser.toDto(user))
                             .collect(Collectors.toList());
    }

    /**
     * @return A user or null
     */
    @Override
    public UserDto userById(Long id) {
        if (Objects.isNull(id))
            return null;
        List<User> users = userRepository.findAll(UserSpec.userIdIs(id));
        if (users.size() == 1) {
            return users.stream().findFirst().map(user -> UserDtoToUser.toDto(user)).get();
        } else if(users.size() > 1) {
            logRecord.setThrown(new Exception(String.format("There is more than one user with the id ", id)));
            LOGGER.log(logRecord);
            return null;
        }
        logRecord.setMessage(String.format("The user with id: %d does not exist", id));
        LOGGER.log(logRecord);
        return null;
    }

    /**
     * @param startDate
     * @param endDate
     * @return List of users who have registered between startDate and endDate
     */
    @Override
    public List<UserDto> usersRegisteredBetween(LocalDate startDate, LocalDate endDate) {
        return userRepository.findByCreationDateBetween(startDate, endDate)
                .stream()
                .map(user -> UserDtoToUser.toDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> usersRegisteredBetween(Optional<String> startDate, Optional<String> endDate) {
        return userRepository.findAll(UserSpec.usersRegisteredBetween(startDate.map(star -> star.isEmpty() ? null : LocalDate.parse(star)),
                endDate.map(LocalDate::parse)))
                .stream()
                .map(user -> UserDtoToUser.toDto(user))
                .collect(Collectors.toList());
    }
}
