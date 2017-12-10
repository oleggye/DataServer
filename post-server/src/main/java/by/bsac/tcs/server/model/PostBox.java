package by.bsac.tcs.server.model;

public class PostBox {

  private long id;
  private String address;

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

  public PostBox(long id, String address) {

    this.id = id;
    this.address = address;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
