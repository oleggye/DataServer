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
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.NonNull;


@lombok.Data
@lombok.NoArgsConstructor
@lombok.RequiredArgsConstructor()
@lombok.EqualsAndHashCode(exclude={"postboxes"})
@Entity
public class User {

  private String id;
  @lombok.NonNull
  private String login;
  @lombok.NonNull
  private String surname;
  private Set<PostBox> postboxes = new HashSet<>(0);

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public String getId() {
    return id;
  }

  @ManyToMany
  @JoinTable(name = "subscription",
      joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "id_post_box", referencedColumnName = "id"))
  public Set<PostBox> getPostboxes() {
    return postboxes;
  }
}
