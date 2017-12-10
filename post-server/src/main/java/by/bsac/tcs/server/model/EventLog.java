package by.bsac.tcs.server.model;

public class EventLog {

  private long id;
  private long postBoxId;
  private Event event;

  public EventLog(long id, long postBoxId, Event event) {

    this.id = id;
    this.postBoxId = postBoxId;
    this.event = event;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPostBoxId() {
    return postBoxId;
  }

  public void setPostBoxId(long postBoxId) {
    this.postBoxId = postBoxId;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
