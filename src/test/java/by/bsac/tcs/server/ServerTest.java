package by.bsac.tcs.server;

import by.bsac.tcs.server.manager.RequestManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

class ServerTest {

    @Mock
    private RequestManager manager;

    @Before
    void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void startServerTest() {


    }



}