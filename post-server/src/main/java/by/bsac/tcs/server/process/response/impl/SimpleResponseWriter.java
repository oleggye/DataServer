package by.bsac.tcs.server.process.response.impl;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.response.ResponseWriter;
import by.bsac.tcs.server.process.response.exception.ResponseWriterException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleResponseWriter implements ResponseWriter {

  public void write(Socket socket, Request request) throws ResponseWriterException {

    try (OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,
            StandardCharsets.UTF_8)) {

      writer.write(request.getResponse());
    } catch (IOException e) {
      throw new ResponseWriterException("Can't write response", e);
    }
  }
}
