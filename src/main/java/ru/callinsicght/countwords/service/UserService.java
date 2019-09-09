package ru.callinsicght.countwords.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.callinsicght.countwords.model.User;

import static org.apache.log4j.Logger.getLogger;

/**
 * TODO: comment
 *
 * @author Alexander Kaleganov
 * @version 0.0.1
 */
@Component
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static Logger LOGGER = getLogger(UserService.class);

    public void userEncodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
