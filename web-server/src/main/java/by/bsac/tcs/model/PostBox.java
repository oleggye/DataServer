package by.bsac.tcs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostBox {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String address;

  public PostBox(long id, String address) {

    this.id = id;
    this.address = address;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
