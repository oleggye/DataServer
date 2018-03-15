package by.bsac.tcs.server.process.parser.impl.parser;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.impl.Method;

public interface RequestParser {

  Request parse(String requestString);
}
