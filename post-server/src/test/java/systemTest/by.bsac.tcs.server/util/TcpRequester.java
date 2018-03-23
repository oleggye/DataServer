package by.bsac.tcs.server.util;

import by.bsac.tcs.server.util.model.Request;
import by.bsac.tcs.server.util.model.RequestBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpRequester {

  private static final Logger LOGGER = LoggerFactory.getLogger(TcpRequester.class);

  private static final int DEFAULT_EXECUTOR_SERVICE_THREADS_COUNT = 2;
  private static final ExecutorService executor = Executors
      .newFixedThreadPool(DEFAULT_EXECUTOR_SERVICE_THREADS_COUNT);

  private static final String TIME_EXCEED_MESSAGE_FORMAT = "Time out of %d %s is exceeded";

  private static final long DEFAULT_TIMEOUT = 6;
  private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

  private final long timeout;
  private final TimeUnit timeUnit;

  public TcpRequester() {
    this.timeout = DEFAULT_TIMEOUT;
    this.timeUnit = DEFAULT_TIME_UNIT;
  }

  public TcpRequester(long timeout, TimeUnit timeUnit) {
    this.timeout = timeout;
    this.timeUnit = timeUnit;
  }

  /**
   * Open socket to a server
   */
  public String perform(RequestBuilder requestBuilder) {
    Request request = requestBuilder.build();
    try (Socket socket = new Socket(request.getServerAddress(), request.getPort())) {

      writeRequest(socket, request.getRequestData());

      return readResponse(socket);
    } catch (IOException e) {
      final String message = "An exception occurred while perform request";
      LOGGER.error(message, e);
      throw new RuntimeException(e);
    }

  }

  private void writeRequest(Socket socket, String requestData) throws IOException {
    OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(),
        StandardCharsets.UTF_8);

    out.append(requestData);
    out.flush();
  }

  private String readResponse(Socket socket) {
    try {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

      Callable<String> responseCallable = prepareResponseCallable(reader);
      Future<String> responseFuture = executor.submit(responseCallable);

      return responseFuture.get(timeout, timeUnit);

    } catch (TimeoutException e) {
      final String message = String.format(TIME_EXCEED_MESSAGE_FORMAT, timeout, timeUnit.name());
      throw new NoResponseException(message, e);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private Callable<String> prepareResponseCallable(BufferedReader reader) {
    return reader::readLine;
  }
}
