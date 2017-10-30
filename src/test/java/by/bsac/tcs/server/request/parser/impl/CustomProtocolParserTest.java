package by.bsac.tcs.server.request.parser.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import by.bsac.tcs.server.request.Request;
import by.bsac.tcs.server.request.parser.ProtocolParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.InputStream;
import java.net.Socket;

public class CustomProtocolParserTest {

    private ProtocolParser parser;

    @Mock
    private Socket socket;

    @Spy
    private InputStream inputStream;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        parser = new CustomProtocolParser();
       // inputStream = socket.getInputStream();
    }

    @Test
    public void parse() throws Exception {
        Request expectedRequest = new Request();
        when(parser.parse(socket)).thenReturn(expectedRequest);
        when(socket.getInputStream()).thenReturn(inputStream);
        doReturn(100).when(inputStream).read();

        Request takenRequest = parser.parse(socket);

        Assert.assertEquals(expectedRequest, takenRequest);

        verify(parser, times(1)).parse(socket);
        verifyNoMoreInteractions(parser);
        verify(socket, times(1)).getInputStream();
        verifyNoMoreInteractions(socket);
    }

}