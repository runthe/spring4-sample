package com.soo.repository;

import com.soo.Application;
import com.soo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author KHS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void crud() {

        // given
        User user = new User();
        user.setEmail("runthe@naver.com");
        user.setName("runthe");
        user.setPassword("runthe");

        // when
        userRepository.save(user);

        // then
        assertThat(userRepository.findAll().size(), is(2));
    }

}