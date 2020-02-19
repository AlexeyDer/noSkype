package eltex.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Класс User
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends AbstractUser {
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    private String email;
    private String phone;


    public User(String u, String p) {
        super(u, p);
    }
}
