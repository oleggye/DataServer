package by.bsac.tcs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventLog {

  private long id;

  private long postBoxId;

  private Event event;

  public EventLog() {
  }

  public EventLog(long id, long postBoxId, Event event) {

    this.id = id;
    this.postBoxId = postBoxId;
    this.event = event;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
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
