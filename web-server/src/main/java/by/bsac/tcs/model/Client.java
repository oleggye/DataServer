package by.bsac.tcs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

  private String id;
  private String login;

  public Client() {
  }

  public Client(String id, String login) {
    this.id = id;
    this.login = login;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Override
  public String toString() {
    return "Client{" +
        "id='" + id + '\'' +
        ", login='" + login + '\'' +
        '}';
  }
}
