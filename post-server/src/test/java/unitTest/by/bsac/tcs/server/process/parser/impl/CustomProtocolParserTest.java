package by.bsac.tcs.server.process.parser.impl;

import static java.util.Objects.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomProtocolParserTest {

  private ProtocolParser parser;

  @Mock
  private Socket socket;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    parser = new CustomProtocolParser();
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedToLongRequest() throws IOException, ProtocolParseException {
    final String userRequest = "111111111111111111111111111111111";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedNullRequest() throws IOException, ProtocolParseException {
    final String userRequest = null;
    prepareUserRequestAndParse(userRequest);
  }

  //REG
  @Test()
  public void testParseWhenPassedCorrectRegMethodThanOk()
      throws IOException, ProtocolParseException {
    final String userRequest = "REG:222850";
    Request parse = prepareUserRequestAndParse(userRequest);

    assertNotNull(parse);
    assertEquals(Method.REG, parse.getMethod());
    assertEquals(222850, parse.getPostBoxId());
    assertEquals(0, parse.getLettersCount());
    assertEquals(0, parse.getEpochTime());
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedRegMethodWithoutIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "REG:";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedRegMethodWithAlphabeticCharacterInIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "REG:22g850";
    prepareUserRequestAndParse(userRequest);
  }

  //LETTER
  @Test()
  public void testParseWhenPassedCorrectLetterMethodThanOk()
      throws IOException, ProtocolParseException {
    final String userRequest = "LETTER:222850:5:1519800922";
    Request parse = prepareUserRequestAndParse(userRequest);

    assertNotNull(parse);
    assertEquals(Method.LETTER, parse.getMethod());
    assertEquals(222850, parse.getPostBoxId());
    assertEquals(5, parse.getLettersCount());
    assertEquals(1519800922, parse.getEpochTime());
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedLetterMethodWithoutIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "LETTER::5:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedLetterMethodWithAlphabeticCharacterInIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "LETTER:222s50:5:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedLetterMethodWithoutQuantityThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "LETTER:222850::1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedLetterMethodWithoutTimeThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "LETTER:222850:5:";
    prepareUserRequestAndParse(userRequest);
  }

  //EMPTY
  @Test()
  public void testParseWhenPassedCorrectEmptyMethodThanOk()
      throws IOException, ProtocolParseException {
    final String userRequest = "EMPTY:222850:1519800922";
    Request parse = prepareUserRequestAndParse(userRequest);

    assertNotNull(parse);
    assertEquals(Method.EMPTY, parse.getMethod());
    assertEquals(222850, parse.getPostBoxId());
    assertEquals(1519800922, parse.getEpochTime());
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedEmptyMethodWithoutIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "EMPTY::1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedEmptyMethodWithAlphabeticCharacterInIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "EMPTY:222f50:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedEmptyMethodWithoutTimeThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "EMPTY:222850:5:";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedEmptyMethodWithoutEolThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "EMPTY:222850:5:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  //WITHDRAWN
  @Test()
  public void testParseWhenPassedCorrectWithdrawnMethodThanOk()
      throws IOException, ProtocolParseException {
    final String userRequest = "WITHDRAWN:222850:1519800922";
    Request parse = prepareUserRequestAndParse(userRequest);

    assertNotNull(parse);
    assertEquals(Method.WITHDRAWN, parse.getMethod());
    assertEquals(222850, parse.getPostBoxId());
    assertEquals(1519800922, parse.getEpochTime());
    assertEquals(0, parse.getLettersCount());
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedWithdrawnMethodWithoutIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "WITHDRAWN::1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedWithdrawnMethodWithAlphabeticCharacterInIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "WITHDRAWN:222f50:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedWithdrawnMethodWithoutTimeThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "WITHDRAWN:222850:";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedWithdrawnMethodWithoutEolThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "WITHDRAWN:222850:5:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  //I_ALIVE
  @Test
  public void testParseWhenPassedCorrectKeepAliveMethodThanOk()
      throws IOException, ProtocolParseException {
    final String userRequest = "I_ALIVE:222850:5:1519800922";
    Request parse = prepareUserRequestAndParse(userRequest);

    assertNotNull(parse);
    assertEquals(Method.KEEP_ALIVE, parse.getMethod());
    assertEquals(222850, parse.getPostBoxId());
    assertEquals(5, parse.getLettersCount());
    assertEquals(1519800922, parse.getEpochTime());
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedKeepAliveMethodWithoutIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "I_ALIVE::5:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedKeepAliveMethodWithAlphabeticCharacterInIdThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "I_ALIVE:222f50:5:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedKeepAliveMethodWithoutQuantityThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "I_ALIVE:222850:1519800922";
    prepareUserRequestAndParse(userRequest);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedKeepAliveMethodWithoutTimeThanException()
      throws IOException, ProtocolParseException {
    final String userRequest = "I_ALIVE:222850:5:";
    prepareUserRequestAndParse(userRequest);
  }

  private InputStream prepareInputStream(final String userRequest)
      throws IOException {
    InputStream stream;

    if (nonNull(userRequest)) {
      stream = new ByteArrayInputStream(userRequest.getBytes(StandardCharsets.UTF_8));
    } else {
      //simulate, when readLine() returns null
      //can't answer why it works with spy and doesn't with mock
      stream = spy(InputStream.class);
      when(stream.read()).thenReturn(-1);
    }
    return stream;
  }

  private Request prepareUserRequestAndParse(final String userRequest)
      throws IOException, ProtocolParseException {

    final InputStream stream = prepareInputStream(userRequest);
    when(socket.getInputStream()).thenReturn(stream);

    Request parse = parser.parse(socket);

    verifySocket();
    return parse;
  }

  private void verifySocket() throws IOException {
    verify(socket).getInputStream();
    verifyNoMoreInteractions(socket);
  }
}