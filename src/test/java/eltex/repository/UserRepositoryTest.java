package eltex.repository;

import eltex.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Интеграционные тесты показывающие базовое использование {@link UserRepository}
 *
 * @author "Alexey Derevtsov"
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserRepositoryTest {
    /**
     * Поле тестируемого репозитория
     */
    @Autowired
    private UserRepository userRepository;

    User user, userTwo;

    @Before
    public void setUp() {
        user = new User("name", "pass");
        userTwo = new User("name1", "pass");
    }

    /**
     * Метод тестирует поиск пользователя по его имени
     */
    @Test
    public void findByUsername() {
        user = userRepository.save(userTwo);
        User found = userRepository.findByUsername(user.getUsername());
        Assert.assertEquals(found.getUsername(), user.getUsername());
    }

    /**
     * Метод тестирует поиск пользователя по id
     */
    @Test
    public void findById() {
        user = userRepository.save(user);
        User found = userRepository.findById(user.getId());
        Assert.assertEquals(found.getId(), user.getId());
    }
}