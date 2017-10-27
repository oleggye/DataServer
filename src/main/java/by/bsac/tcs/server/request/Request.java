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
}
