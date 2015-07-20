package com.soo.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author KHS
 */
public class UserTest {

    @Test
    public void getterSetter() {
        User user = new User();
        String email = "runthe@naver.com";
        user.setEmail(email);

        assertThat(user.getEmail(), is(email));
    }

}