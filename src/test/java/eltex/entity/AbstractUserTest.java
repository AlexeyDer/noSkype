package eltex.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;
/**
 * Класс для тестирования абстарктного класса AbstractUser
 * Тестируются геттеры и сеттеры
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
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