package by.bsac.tcs.domain.controller.command;

import by.bsac.tcs.server.model.Request;

public interface Command {

  void execute(Request request) throws CommandException;
}
