package eltex.service;

import eltex.entity.Role;
import eltex.entity.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
/**
 * Класс для тестирования страницы класса UserService
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    /**
     * Метод, который проверяет :
     *  * Создается ли пользователь
     *  * Присваивается ли ему нужная роль(Admin или User)
     *  * Проверка поля активности пользователя после создания
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