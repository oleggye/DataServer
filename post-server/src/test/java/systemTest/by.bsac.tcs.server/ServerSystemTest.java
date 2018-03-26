package by.bsac.tcs.server;

import static org.junit.Assert.assertEquals;

import by.bsac.tcs.server.util.TcpRequester;
import by.bsac.tcs.server.util.model.RequestBuilder;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServerSystemTest {

  private static final String SERVER_ADDRESS = "127.0.0.1";
  private static final int SERVER_PORT = 8888;
  private static final long SLEEP_TIMEOUT_SEC = 3;

  private static Server server;
  private static Thread serverThread;

  /**
   * Start the server as a new thread add save ref to it
   */
  @BeforeClass
  public static void setUpBeforeClass() throws InterruptedException {
    serverThread = new Thread(() -> {
      server = new Server(SERVER_PORT);
      server.start();
    });
    serverThread.start();
    TimeUnit.SECONDS.sleep(SLEEP_TIMEOUT_SEC);
  }

  /**
   * Don't need to use delay before server stop because of {@link TcpRequester} implementation
   */
  @AfterClass
  public static void tearDownAfterClass() {
    if (server != null) {
      server.stop();
    }
    if (serverThread != null && serverThread.isAlive()) {
      serverThread.interrupt();
    }
  }

  @Test(/*expected = NoResponseException.class*/)
  public void testWhenSendRequestWithEmptyData() {
    final String data = "";
    String response = performRequest(data);
    assertEquals(null, response);
  }

  @Test(/*expected = NoResponseException.class*/)
  public void testWhenSendVeryLongRequest() {
    final String data = "111111111111111111111111111111111111";
    String response = performRequest(data);
    assertEquals(null, response);
  }

  @Test
  public void testWhenSendRegRequest() {
    final String data = "REG:222850\n";

    String response = performRequest(data);

    assertEquals("REGISTERED", response);
  }

  @Test
  public void testWhenSendLetterRequest() {
    final String data = "LETTER:222850:5:1519800922\n";

    String response = performRequest(data);

    assertEquals("LETTER_REGISTERED", response);
  }

  @Test
  public void testWhenSendEmptyRequest() {
    final String data = "EMPTY:222850:1519800922\n";

    String response = performRequest(data);

    assertEquals("EMPTY_REGISTERED", response);
  }

  @Test
  public void testWhenSendWithdrawnRequest() {
    final String data = "WITHDRAWN:222850:1519800922\n";

    String response = performRequest(data);

    assertEquals("WITHDRAWN_REGISTERED", response);
  }

  @Test
  public void testWhenSendKeepAliveRequest() {
    final String data = "I_ALIVE:222850:5:1519800922\n";

    String response = performRequest(data);

    assertEquals("I_ALIVE_REGISTERED", response);
  }

  private String performRequest(String data) {
    return new TcpRequester()
        .perform(
            new RequestBuilder()
                .serverAddress(SERVER_ADDRESS)
                .serverPort(SERVER_PORT)
                .requestData(data));
  }
}