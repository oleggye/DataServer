package by.bsac.tcs.domain.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import by.bsac.tcs.domain.dao.EventLogDao;
import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.exception.EventServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

  @InjectMocks
  private EventServiceImpl eventLogService;

  @Mock
  private EventLogDao eventLogDAO;

  @Mock
  private EventLog eventLog;

  @Test
  public void testLog() throws Exception {
    eventLogService.log(eventLog);

    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test
  public void testRegisteredSuccess() throws Exception {
    String result = eventLogService.register(eventLog);

    Assert.assertEquals("REGISTERED", result);
    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testRegisteredFail() throws Exception {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.register(eventLog);
  }

  @Test
  public void testChangedSuccess() throws Exception {
    String result = eventLogService.changed(eventLog);

    Assert.assertEquals("LETTER_REGISTERED", result);
    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testChangedFail() throws Exception {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.changed(eventLog);
  }

  @Test
  public void testOpenedSuccess() throws Exception {
    String result = eventLogService.opened(eventLog);

    Assert.assertEquals("WITHDRAWN_REGISTERED", result);
    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testOpenedFail() throws Exception {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.opened(eventLog);
  }

  @Test
  public void testClosedSuccess() throws Exception {
    String result = eventLogService.closed(eventLog);

    Assert.assertEquals("EMPTY_REGISTERED", result);
    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testClosedFail() throws Exception {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.closed(eventLog);
  }

  @Test
  public void testKeepAliveSuccess() throws Exception {
    eventLogService.keepAlive(eventLog);

    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testKeepAliveFail() throws Exception {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.keepAlive(eventLog);
  }

}