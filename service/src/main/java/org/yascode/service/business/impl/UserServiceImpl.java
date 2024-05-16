package org.yascode.service.business.impl;

import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.User;
import org.yascode.persistence.repository.UserRepository;
import org.yascode.persistence.repository.specification.UserSpec;
import org.yascode.service.business.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private Logger LOGGER = Logger.getLogger(getClass().getName());
    private LogRecord logRecord = new LogRecord(Level.INFO, null);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @return all users
     */
    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    /**
     * @return A user or null
     */
    @Override
    public User userById(Long id) {
        if (Objects.isNull(id))
            return null;
        List<User> users = userRepository.findAll(UserSpec.userIdIs(id));
        if (users.size() == 1) {
            return users.stream().findFirst().isPresent() ? users.stream().findFirst().get() : null;
        }
        logRecord.setMessage(String.format("The user with id: %d does not exist", id));
        logRecord.setLoggerName(LOGGER.getName());
        logRecord.setThrown(new Exception(String.format("The user with id: %d does not exist", id)));
        LOGGER.log(logRecord);
        return null;
    }

    /**
     * @param startDate
     * @param endDate
     * @return List of users who have registered between startDate and endDate
     */
    @Override
    public List<User> usersRegisteredBetween(LocalDate startDate, LocalDate endDate) {
        return userRepository.findByCreationDateBetween(startDate, endDate);
    }
}
