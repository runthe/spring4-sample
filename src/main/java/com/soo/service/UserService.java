package com.soo.service;

import com.soo.domain.User;
import com.soo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author KHS
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        user.setDate(new Date());
        return userRepository.save(user);
    }

    public User updateUser(User userUpdate) {

        User user = userRepository.findOne(userUpdate.getNumber());

        String newUsername = userUpdate.getName();
        if (!StringUtils.isEmpty(newUsername)) {
            user.setName(newUsername);
        }

        String newPassword = userUpdate.getPassword();
        if (!StringUtils.isEmpty(newPassword)) {
            user.setPassword(newPassword);
        }

        return userRepository.save(user);

    }
}
