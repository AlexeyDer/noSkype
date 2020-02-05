package eltex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Абстрактный класс с базовыми полями, которые будут использоваться
 * в подобных сущностях
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractUser {
    /** Поле идентификатора */
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    /** Поле имени и логина пользователя */
    private String username;
    /** Поле пароля пользователя */
    private String password;
    /** Поле повторного пароля пользователя */
    private String confirmPassword;

    public AbstractUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
