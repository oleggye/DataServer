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
import by.bsac.tcs.domain.service.exception.ServiceException;
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
  public void testLog() throws ServiceException, DaoException {
    eventLogService.log(eventLog);

    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test
  public void testRegisteredSuccess() throws ServiceException, DaoException {
    eventLogService.register(eventLog);
    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testRegisteredFail() throws ServiceException, DaoException {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.register(eventLog);
  }

  @Test
  public void testChangedSuccess() throws ServiceException, DaoException {
    eventLogService.changed(eventLog);
    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testChangedFail() throws ServiceException, DaoException {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.changed(eventLog);
  }

  @Test
  public void testOpenedSuccess() throws ServiceException, DaoException {
    eventLogService.opened(eventLog);
    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testOpenedFail() throws ServiceException, DaoException {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.opened(eventLog);
  }

  @Test
  public void testClosedSuccess() throws ServiceException, DaoException {
    eventLogService.closed(eventLog);
    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testClosedFail() throws ServiceException, DaoException {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.closed(eventLog);
  }

  @Test
  public void testKeepAliveSuccess() throws ServiceException, DaoException {
    eventLogService.keepAlive(eventLog);

    verify(eventLogDAO, times(1)).save(eventLog);
    verifyNoMoreInteractions(eventLogDAO);
  }

  @Test(expected = EventServiceException.class)
  public void testKeepAliveFail() throws ServiceException, DaoException {
    doThrow(DaoException.class).when(eventLogDAO).save(any(EventLog.class));
    eventLogService.keepAlive(eventLog);
  }

}