package by.bsac.tcs.server.process.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import java.io.ByteArrayInputStream;
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

  //REG
  @Test()
  public void testParseWhenPassedCorrectRegMethodThanOk() throws Exception {
    final String userRequest = "REG:222850";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    Request parse = parser.parse(socket);

    assertNotNull(parse);
    assertEquals(Method.REG.name(), parse.getRequestParam("method"));
    assertEquals("222850", parse.getRequestParam("id"));

    verify(socket).getInputStream();
    verifyNoMoreInteractions(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedRegMethodWithoutIdThanException() throws Exception {
    final String userRequest = "REG:";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedRegMethodWithAlphabeticCharacterInIdThanException()
      throws Exception {
    final String userRequest = "REG:22g850";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  //LIST
  @Test()
  public void testParseWhenPassedCorrectListMethodThanOk() throws Exception {
    final String userRequest = "LIST:222850:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    Request parse = parser.parse(socket);

    assertNotNull(parse);
    assertEquals(Method.LIST.name(), parse.getRequestParam("method"));
    assertEquals("222850", parse.getRequestParam("id"));
    assertEquals("1519800922", parse.getRequestParam("time"));

    verify(socket).getInputStream();
    verifyNoMoreInteractions(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedListMethodWithoutIdThanException() throws Exception {
    final String userRequest = "LIST::1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedListMethodWithAlphabeticCharacterInIdThanException()
      throws Exception {
    final String userRequest = "LIST:222s50:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedListMethodWithoutTimeThanException() throws Exception {
    final String userRequest = "LIST:222850:";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  //EMPTY
  @Test()
  public void testParseWhenPassedCorrectEmptyMethodThanOk() throws Exception {
    final String userRequest = "EMPTY:222850:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    Request parse = parser.parse(socket);

    assertNotNull(parse);
    assertEquals(Method.EMPTY.name(), parse.getRequestParam("method"));
    assertEquals("222850", parse.getRequestParam("id"));
    assertEquals("1519800922", parse.getRequestParam("time"));

    verify(socket).getInputStream();
    verifyNoMoreInteractions(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedEmptyMethodWithoutIdThanException() throws Exception {
    final String userRequest = "EMPTY::1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedEmptyMethodWithAlphabeticCharacterInIdThanException()
      throws Exception {
    final String userRequest = "EMPTY:222f50:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedEmptyMethodWithoutTimeThanException() throws Exception {
    final String userRequest = "EMPTY:222850:5:";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedEmptyMethodWithoutEolThanException() throws Exception {
    final String userRequest = "EMPTY:222850:5:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  //WITHDRAWN
  @Test()
  public void testParseWhenPassedCorrectWithdrawnMethodThanOk() throws Exception {
    final String userRequest = "WITHDRAWN:222850:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    Request parse = parser.parse(socket);

    assertNotNull(parse);
    assertEquals(Method.WITHDRAWN.name(), parse.getRequestParam("method"));
    assertEquals("222850", parse.getRequestParam("id"));
    assertEquals("1519800922", parse.getRequestParam("time"));

    verify(socket).getInputStream();
    verifyNoMoreInteractions(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedWithdrawnMethodWithoutIdThanException() throws Exception {
    final String userRequest = "WITHDRAWN::1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedWithdrawnMethodWithAlphabeticCharacterInIdThanException()
      throws Exception {
    final String userRequest = "WITHDRAWN:222f50:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedWithdrawnMethodWithoutTimeThanException() throws Exception {
    final String userRequest = "WITHDRAWN:222850:";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedWithdrawnMethodWithoutEolThanException() throws Exception {
    final String userRequest = "WITHDRAWN:222850:5:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  //KEEP_ALIVE
  @Test()
  public void testParseWhenPassedCorrectKeepAliveMethodThanOk() throws Exception {
    final String userRequest = "KEEP_ALIVE:222850:5:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    Request parse = parser.parse(socket);

    assertNotNull(parse);
    assertEquals(Method.KEEP_ALIVE.name(), parse.getRequestParam("method"));
    assertEquals("222850", parse.getRequestParam("id"));
    assertEquals("5", parse.getRequestParam("count"));
    assertEquals("1519800922", parse.getRequestParam("time"));

    verify(socket).getInputStream();
    verifyNoMoreInteractions(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedKeepAliveMethodWithoutIdThanException() throws Exception {
    final String userRequest = "KEEP_ALIVE::5:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedKeepAliveMethodWithAlphabeticCharacterInIdThanException()
      throws Exception {
    final String userRequest = "KEEP_ALIVE:222f50:5:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedKeepAliveMethodWithoutQuantityThanException() throws Exception {
    final String userRequest = "KEEP_ALIVE:222850:1519800922";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  @Test(expected = ProtocolParseException.class)
  public void testParseWhenPassedKeepAliveMethodWithoutTimeThanException() throws Exception {
    final String userRequest = "KEEP_ALIVE:222850:5:";

    InputStream stream = prepareInputStream(userRequest);

    when(socket.getInputStream()).thenReturn(stream);

    parser.parse(socket);
  }

  private InputStream prepareInputStream(final String userRequest) {
    return new ByteArrayInputStream(userRequest.getBytes(StandardCharsets.UTF_8));
  }
}