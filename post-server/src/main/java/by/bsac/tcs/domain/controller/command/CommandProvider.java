package by.bsac.tcs.domain.controller.command;

import by.bsac.tcs.domain.controller.command.impl.HasClosedCommand;
import by.bsac.tcs.domain.controller.command.impl.HasOpenedCommand;
import by.bsac.tcs.domain.controller.command.impl.KeepAliveCommand;
import by.bsac.tcs.domain.controller.command.impl.QuantityChangedCommand;
import by.bsac.tcs.domain.controller.command.impl.RegistrationCommand;
import by.bsac.tcs.domain.controller.command.impl.WrongCommand;
import by.bsac.tcs.server.process.parser.impl.Method;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommandProvider.class);

  private final Map<Method, Command> commandStore;

  private final Command wrongCommand = new WrongCommand();

  private CommandProvider() {
    commandStore = new EnumMap<>(Method.class);
    commandStore.put(Method.REG, new RegistrationCommand());
    commandStore.put(Method.LIST, new QuantityChangedCommand());
    commandStore.put(Method.WITHDRAWN, new HasOpenedCommand());
    commandStore.put(Method.EMPTY, new HasClosedCommand());
    commandStore.put(Method.KEEP_ALIVE, new KeepAliveCommand());
  }

  public static CommandProvider getInstance() {
    return SingletonHolder.getInstance();
  }

  public Command getCommand(Method method) {
    LOGGER.info("getCommand by method {}", method);

    if (commandStore.containsKey(method)) {
      return commandStore.get(method);
    }
    LOGGER.warn("wrong method {} found!", method);
    return wrongCommand;
  }

  public Command getCommand(String methodName) {
    if (Objects.isNull(methodName)) {
      LOGGER.warn("methodName is null !");
      return wrongCommand;
    }
    Method method = Method.valueOf(methodName.toUpperCase());
    return getCommand(method);
  }

  private static class SingletonHolder {

    private static final CommandProvider INSTANCE = new CommandProvider();

    private SingletonHolder() {
    }

    public static CommandProvider getInstance() {
      return INSTANCE;
    }
  }
}
