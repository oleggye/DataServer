package by.bsac.tcs.logic.model;

import by.bsac.tcs.server.model.RequestType;

public class EventLog {

  private long id;
  private long postBoxId;
  private RequestType requestType;

  public EventLog(long id, long postBoxId, RequestType requestType) {

    this.id = id;
    this.postBoxId = postBoxId;
    this.requestType = requestType;
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

  public RequestType getRequestType() {
    return requestType;
  }

  public void setRequestType(RequestType requestType) {
    this.requestType = requestType;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
