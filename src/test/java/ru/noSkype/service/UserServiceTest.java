package ru.noSkype.service;

import ru.noSkype.entity.Role;
import ru.noSkype.entity.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Интеграционные тесты показывающие базовое использование {@link UserService}
 *
 * @author "Alexey Derevtsov"
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    /**
     * Метод, который проверяет :
     * * Создается ли пользователь
     * * Присваивается ли ему нужная роль(Admin или User)
     * * Проверка поля активности пользователя после создания
     */
    @Test
    public void registNewUserAndAdmin() {
        User user = new User("user", "pass");
        User admin = new User("adm", "pass");

        Boolean isUserCreated = userService.registNewUser(user, false);
        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(user.isActive());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Boolean isAdminCreated = userService.registNewUser(admin, true);
        Assert.assertTrue(isAdminCreated);
        Assert.assertTrue(admin.isActive());
        Assert.assertTrue(CoreMatchers.is(admin.getRoles()).matches(Collections.singleton(Role.ADMIN)));
    }

}