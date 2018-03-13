package by.bsac.tcs.server.process.response;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.response.exception.ResponseWriterException;
import java.net.Socket;

public interface ResponseWriter {

  void write(Socket socket, Request request) throws ResponseWriterException;
}
