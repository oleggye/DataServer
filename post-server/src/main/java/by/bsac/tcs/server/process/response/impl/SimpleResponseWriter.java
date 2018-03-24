package by.bsac.tcs.server.process.response.impl;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.response.ResponseWriter;
import by.bsac.tcs.server.process.response.exception.ResponseWriterException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleResponseWriter implements ResponseWriter {

  /**
   * When close() method will be invoked on the socket - the socket will be close so, I am using
   * this ability
   */
  public void write(Socket socket, Request request) throws ResponseWriterException {

    String response = request.getResponse();
    try (OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,
            StandardCharsets.UTF_8)) {

      if (isNotBlank(response)) {
        writer.write(response);
        writer.flush();
      }


    } catch (IOException e) {
      throw new ResponseWriterException("Can't write response", e);
    }
  }
}
