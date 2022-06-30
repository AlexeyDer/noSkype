package ru.noSkype.repository;

import ru.noSkype.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для взаимодействия с сущностью.
 * Можно прямо дополнить его своими методами запросов и выполнять операции.
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Поле для поиска пользователя по его логину
     */
    User findByUsername(String username);

    /**
     * Поле для поиска пользователя по его id
     */
    User findById(Long id);
}
