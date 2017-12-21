package by.bsac.tcs.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@lombok.Data

@lombok.EqualsAndHashCode(exclude={"users"})
@Entity
public class PostBox {

  private long id;
  private String address;
  private Set<User> users = new HashSet<>(0);

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long getId() {
    return id;
  }


  @ManyToMany(mappedBy = "postboxes")
  public Set<User> getUsers() {
    return users;
  }
}
