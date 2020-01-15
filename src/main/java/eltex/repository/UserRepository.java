package eltex.repository;

import eltex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для взаимодействия с сущностью.
 * Можно прямо дополнить его своими методами запросов и выполнять операции.
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
