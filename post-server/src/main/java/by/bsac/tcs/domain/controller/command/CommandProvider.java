package by.bsac.tcs.domain.controller.command;

import by.bsac.tcs.domain.controller.command.impl.HasClosedCommand;
import by.bsac.tcs.domain.controller.command.impl.HasOpenedCommand;
import by.bsac.tcs.domain.controller.command.impl.KeepAliveCommand;
import by.bsac.tcs.domain.controller.command.impl.LogCommand;
import by.bsac.tcs.domain.controller.command.impl.QuantityChangedCommand;
import by.bsac.tcs.domain.controller.command.impl.RegistrationCommand;
import by.bsac.tcs.domain.controller.command.impl.WrongCommand;
import by.bsac.tcs.domain.model.Event;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommandProvider.class);

  private final Map<Event, Command> commandStore;

  private final Command wrongCommand = new WrongCommand();

  private CommandProvider() {
    final Map<Event, Command> map = new EnumMap<>(Event.class);
    map.put(Event.REGISTRATION, new RegistrationCommand());
    map.put(Event.KEEP_ALIVE, new KeepAliveCommand());
    map.put(Event.HAS_OPENED, new HasOpenedCommand());
    map.put(Event.HAS_CLOSED, new HasClosedCommand());
    map.put(Event.QUANTITY_CHANGED, new QuantityChangedCommand());
    map.put(Event.LOG, new LogCommand());

    commandStore = map;
  }

  public static CommandProvider getInstance() {
    return SingletonHolder.getInstance();
  }

  public Command getCommand(Event event) {
    LOGGER.info("getCommand by event {}", event);

    if (commandStore.containsKey(event)) {
      return commandStore.get(event);
    }
    LOGGER.warn("wrong event {} found!", event);
    return wrongCommand;
  }

  public Command getCommand(String eventName) {
    if (Objects.isNull(eventName)) {
      LOGGER.warn("eventName is null !");
      return wrongCommand;
    }
    Event event = Event.valueOf(eventName.toUpperCase());
    return getCommand(event);
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
