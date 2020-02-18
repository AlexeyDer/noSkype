package eltex.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;
/**
 * Класс для тестирования сущности User
 * Тестируются геттеры и сеттеры
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
public class UserTest {

    @Test
    public void setRoles() {
        User user = new User("Mark", "password", Collections.singleton(Role.USER));
        user.setRoles(Collections.singleton(Role.ADMIN));
        Assert.assertEquals(Collections.singleton(Role.ADMIN), user.getRoles());
    }

    @Test
    public void setEmail() {
        User user = new User("Mark", "password", Collections.singleton(Role.USER), "mark@mail.ru");
        user.setEmail("mark2018@yandex.ru");
        Assert.assertEquals("mark2018@yandex.ru", user.getEmail());
    }

    @Test
    public void getRoles() {
        User user = new User("Mark", "password", Collections.singleton(Role.USER), "mark@mail.ru");
        Assert.assertEquals(Collections.singleton(Role.USER), user.getRoles());
    }

    @Test
    public void getEmail() {
        User user = new User("Mark", "password", Collections.singleton(Role.USER), "mark@mail.ru");
        Assert.assertEquals("mark@mail.ru", user.getEmail());
    }
}