package by.bsac.tcs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@lombok.Data
@Entity
public class EventLog {

  private long id;
  private long postBoxId;
  private Event event;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long getId() {
    return id;
  }

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  public Event getEvent() {
    return event;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
