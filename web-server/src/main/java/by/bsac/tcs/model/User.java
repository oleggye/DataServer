package by.bsac.tcs.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import jdk.nashorn.internal.objects.annotations.Getter;

@Entity
@Getter @Setter @NoArgsConstructor
public class User {

  private String id;
  private String login;
  private String surname;
  private Set<PostBox> postboxes = new HashSet<>(0);

  public User() {
  }

  public User(String id, String login, String surname,
      Set<PostBox> postboxes) {
    this.id = id;
    this.login = login;
    this.surname = surname;
    this.postboxes = postboxes;
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

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @ManyToMany
  @JoinTable(name = "subscription",
      joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "id_post_box", referencedColumnName = "id"))
  public Set<PostBox> getPostboxes() {
    return postboxes;
  }

  public void setPostboxes(Set<PostBox> postboxes) {
    this.postboxes = postboxes;
  }
}
