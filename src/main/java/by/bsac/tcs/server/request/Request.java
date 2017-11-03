package by.bsac.tcs.server.request;

import java.util.HashMap;
import java.util.Map;

public class Request {

  private final Map<String, String> paramMap = new HashMap<>();

  public String getRequestParam(String paramName) {
    return paramMap.get(paramName);
  }

  public void setRequestParam(String key, String val) {
    paramMap.put(key, val);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Request request = (Request) o;

    return paramMap.equals(request.paramMap);
  }

  @Override
  public int hashCode() {
    return paramMap.hashCode();
  }
}
