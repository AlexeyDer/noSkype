package eltex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Класс представления пользователя
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractUser {
    /**
     * Поле статуса пользователя (Активен, не активен)
     */
    private boolean active;
    /**
     * Поле ролей пользователя
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    /**
     * Поле почты пользователя
     */
    private String email;
    /**
     * Поле телефона пользователя
     */
    private String phone;

    public User(String u, String p) {
        super(u, p);
    }
}
