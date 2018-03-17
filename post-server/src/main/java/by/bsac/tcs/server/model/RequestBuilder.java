package by.bsac.tcs.server.model;

import by.bsac.tcs.server.process.parser.impl.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder class for {@link Request}
 */
public class RequestBuilder {

  private final Map<String, String> paramMap = new HashMap<>();
  private Method method;
  private long postBoxId;
  private int lettersCount;
  private long epochTime;

  public RequestBuilder method(Method method) {
    this.method = method;
    return this;
  }

  public RequestBuilder id(long postBoxId) {
    this.postBoxId = postBoxId;
    return this;
  }

  public RequestBuilder id(String postBoxId) {
    this.postBoxId = Integer.valueOf(postBoxId);
    return this;
  }

  public RequestBuilder lettersCount(int lettersCount) {
    this.lettersCount = lettersCount;
    return this;
  }

  public RequestBuilder lettersCount(String lettersCount) {
    this.lettersCount = Integer.parseInt(lettersCount);
    return this;
  }

  public RequestBuilder time(String longEpochTime) {
    this.epochTime = Long.parseLong(longEpochTime);
    return this;
  }

  public RequestBuilder time(long epochTime) {
    this.epochTime = epochTime;
    return this;
  }

  public RequestBuilder param(String key, String val) {
    paramMap.put(key, val);
    return this;
  }

  public Request build() {
    Request request = new Request(method);
    request.setPostBoxId(postBoxId);
    request.setLettersCount(lettersCount);
    request.setEpochTime(epochTime);

    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
      request.setRequestParam(entry.getKey(), entry.getValue());
    }
    return request;
  }
}
