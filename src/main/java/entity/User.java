package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *  Класс User
 *
 *  @author "Viktoria"
 *  @version 1.0.0
 *
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

  @GeneratedValue(strategy = GenerationType.TABLE) @Id private Integer id;
  private String fio;
  private String phone;

  public User(String fio, String phone) {
    this.fio = fio;
    this.phone = phone;
  }
}
