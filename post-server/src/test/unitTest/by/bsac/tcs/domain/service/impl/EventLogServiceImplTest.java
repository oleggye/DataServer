package by.bsac.tcs.domain.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import by.bsac.tcs.domain.dao.EventLogDAO;
import by.bsac.tcs.domain.model.Event;
import by.bsac.tcs.domain.model.EventLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventLogServiceImplTest {

  @InjectMocks
  private EventLogServiceImpl eventLogService;

  @Mock
  private EventLogDAO eventLogDAO;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void log() throws Exception {
    final EventLog eventLog = new EventLog(1, Event.LOG,"test");

    eventLogService.log(eventLog);

    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

}