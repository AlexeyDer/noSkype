package eltex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.List;

/**
 *  Класс Person (наследуется от User)
 *  Сущность для описания пользователя, дополнительно содержит:
 *      логин
 *      пароль
 *      почта
 *      список друзей
 *      статус (online, offline)
 *      когда был в сети последний раз
 *
 *  @author "Viktoria"
 *  @version 1.0.0
 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person extends User {

    private String login;
    private String password; // следует хешировать
    private String mail; // поле следует проверять на правильность
    // private List<Person> friend;
    private String status;
    private String dateOfLastVisit;

    public Person(Integer id, String fio, String phone, String login) {
        super(id, fio, phone);
        this.login = new String(login);
        this.password = "none";
        this.mail = "mail@mail.ru";
        // this.friend = null;
        this.status = "offline";
        this.dateOfLastVisit = "today";
    }

    @Override
    public String toString(){
        return "[id = " + super.getId() + ", login = " + this.login + "]";
    }

}
