package by.bsac.tcs.domain.model;

public class EventLog {

  private long id;
  private long postBoxId;
  private Event event;
  private String state;

  public EventLog(long postBoxId, Event event, String state) {
    this.postBoxId = postBoxId;
    this.event = event;
    this.state = state;
  }

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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    EventLog eventLog = (EventLog) o;

    if (id != eventLog.id) {
      return false;
    }
    if (postBoxId != eventLog.postBoxId) {
      return false;
    }
    if (event != eventLog.event) {
      return false;
    }
    return state != null ? state.equals(eventLog.state) : eventLog.state == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (int) (postBoxId ^ (postBoxId >>> 32));
    result = 31 * result + (event != null ? event.hashCode() : 0);
    result = 31 * result + (state != null ? state.hashCode() : 0);
    return result;
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
