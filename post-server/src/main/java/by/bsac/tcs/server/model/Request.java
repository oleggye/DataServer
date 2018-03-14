package by.bsac.tcs.server.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Request {

  private RequestType requestType;
  private final Map<String, String> paramMap = new HashMap<>();
  private String response;

  public RequestType getRequestType() {
    return requestType;
  }

  public void setRequestType(RequestType requestType) {
    this.requestType = requestType;
  }

  public String getRequestParam(String paramName) {
    return paramMap.get(paramName);
  }

  public void setRequestParam(String key, String val) {
    paramMap.put(key, val);
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
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
    return requestType == request.requestType
        && Objects.equals(paramMap, request.paramMap)
        && Objects.equals(response, request.response);
  }

  @Override
  public int hashCode() {

    return Objects.hash(requestType, paramMap, response);
  }
}
