package by.bsac.tcs.domain.controller.command;

import by.bsac.tcs.domain.controller.command.impl.KeepAliveCommand;
import by.bsac.tcs.domain.controller.command.impl.RegistrationCommand;
import by.bsac.tcs.domain.controller.command.impl.WrongCommand;
import by.bsac.tcs.domain.model.Event;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommandProvider.class);

  private final Map<Event, Command> commandStore;

  private final Command wrongCommand = new WrongCommand();

  private CommandProvider() {
    final Map<Event, Command> map = new HashMap<>();
    map.put(Event.REGISTRATION, new RegistrationCommand());
    map.put(Event.KEEP_ALIVE, new KeepAliveCommand());
    map.put(Event.HAS_OPENED, new HasOpenedCommand());
    map.put(Event.HAS_CLOSED, new HasClosedCommand());
    map.put(Event.QUANTITY_CHANGED, new QuantityChangedCommand());

    commandStore = Collections.unmodifiableMap(map);
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

    public static CommandProvider getInstance() {
      return INSTANCE;
    }
  }
}
