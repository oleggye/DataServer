package by.bsac.tcs.server.process.parser.impl.parser;

import static by.bsac.tcs.server.model.Method.EMPTY;
import static by.bsac.tcs.server.model.Method.KEEP_ALIVE;
import static by.bsac.tcs.server.model.Method.LETTER;
import static by.bsac.tcs.server.model.Method.REG;
import static by.bsac.tcs.server.model.Method.WITHDRAWN;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.process.parser.impl.parser.impl.EmptyParser;
import by.bsac.tcs.server.process.parser.impl.parser.impl.KeepAliveParser;
import by.bsac.tcs.server.process.parser.impl.parser.impl.LetterParser;
import by.bsac.tcs.server.process.parser.impl.parser.impl.RegParser;
import by.bsac.tcs.server.process.parser.impl.parser.impl.WithdrawnParser;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public class ParserFactory {

  private final Map<Method, RequestParser> parserMap;

  private ParserFactory() {
    parserMap =
        ImmutableMap.of(
            REG, new RegParser(REG),
            LETTER, new LetterParser(LETTER),
            WITHDRAWN, new WithdrawnParser(WITHDRAWN),
            EMPTY, new EmptyParser(EMPTY),
            KEEP_ALIVE, new KeepAliveParser(KEEP_ALIVE));
  }

  public static ParserFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public RequestParser getProtocolParser(Method method) {
    return parserMap.get(method);
  }

  private static class SingletonHolder {

    private static final ParserFactory INSTANCE = new ParserFactory();

    public static ParserFactory getInstance() {
      return INSTANCE;
    }
  }
}
