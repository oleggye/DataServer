package by.bsac.tcs.server.model;

import java.util.HashMap;
import java.util.Map;

public class Request {

  private Method method;
  private long postBoxId;
  private int lettersCount;
  private long epochTime;

  private final Map<String, String> paramMap = new HashMap<>();

  private String response;

  public Request(Method method) {
    if (method == null) {
      throw new IllegalArgumentException("Request method can't be null!");
    }
    this.method = method;
  }

  public Method getMethod() {
    return method;
  }

  public long getPostBoxId() {
    return postBoxId;
  }

  public void setPostBoxId(long postBoxId) {
    this.postBoxId = postBoxId;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public int getLettersCount() {
    return lettersCount;
  }

  public void setLettersCount(int lettersCount) {
    this.lettersCount = lettersCount;
  }

  public long getEpochTime() {
    return epochTime;
  }

  public void setEpochTime(long epochTime) {
    this.epochTime = epochTime;
  }

  public String getRequestParam(String paramName) {
    return paramMap.get(paramName);
  }

  public void setRequestParam(String key, String val) {
    paramMap.put(key, val);
  }
}
