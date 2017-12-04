package by.bsac.tcs.server.model;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {

  private final Map<String, String> paramMap = new HashMap<>();

  public RequestBuilder setParam(String key, String val) {
    paramMap.put(key, val);
    return this;
  }

  public Request build() {
    Request request = new Request();
    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
      request.setRequestParam(entry.getKey(), entry.getValue());
    }
    return request;
  }
}
