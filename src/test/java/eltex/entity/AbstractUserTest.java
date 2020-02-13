package eltex.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class AbstractUserTest {

    @Test
    public void setUsername() {
        User user = new User("Mark", "password", Collections.singleton(Role.USER));
        user.setUsername("Pasha");
        Assert.assertEquals("Pasha", user.getUsername());
    }

    @Test
    public void getUsername() {
        User user = new User("Mark", "password", Collections.singleton(Role.USER));
        Assert.assertEquals("Mark", user.getUsername());
    }
}