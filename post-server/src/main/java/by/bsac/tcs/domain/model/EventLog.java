package by.bsac.tcs.domain.model;

public class EventLog {

  private long id;
  private long postBoxId;
  private Event event;
  private String state;

  public EventLog(long id, long postBoxId, Event event, String state) {

    this.id = id;
    this.postBoxId = postBoxId;
    this.event = event;
    this.state = state;
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "EventLog{" +
        "id=" + id +
        ", postBoxId=" + postBoxId +
        ", event=" + event +
        ", state='" + state + '\'' +
        '}';
  }
}
