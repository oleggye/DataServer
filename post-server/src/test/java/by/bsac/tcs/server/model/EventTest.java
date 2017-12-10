package by.bsac.tcs.server.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EventTest {

  @Test
  public void shouldReturnRegistrationRequestTypeByTypeCodeZero() throws Exception {
    final int typeCode = 0;
    final Event expectedEvent = Event.REGISTRATION;

    getRequestTypeAndAssert(typeCode, expectedEvent);
  }

  @Test
  public void shouldReturnKeepAliveRequestTypeByTypeCodeOne() throws Exception {
    final int typeCode = 1;
    final Event expectedEvent = Event.KEEP_ALIVE;

    getRequestTypeAndAssert(typeCode, expectedEvent);
  }

  @Test
  public void shouldReturnHasOpenedRequestTypeByTypeCodeTwo() throws Exception {
    final int typeCode = 2;
    final Event expectedEvent = Event.HAS_OPENED;

    getRequestTypeAndAssert(typeCode, expectedEvent);
  }

  @Test
  public void shouldReturnHasClosedRequestTypeByTypeCodeThree() throws Exception {
    final int typeCode = 3;
    final Event expectedEvent = Event.HAS_CLOSED;

    getRequestTypeAndAssert(typeCode, expectedEvent);
  }

  @Test
  public void shouldReturnHasClosedRequestTypeByTypeCodeTwo() throws Exception {
    final int typeCode = 4;
    final Event expectedEvent = Event.STATE_CHANGED;

    getRequestTypeAndAssert(typeCode, expectedEvent);
  }

  private void getRequestTypeAndAssert(final int typeCode, final Event expectedEvent) {
    final Event takenEvent = Event.getRequestType(typeCode);
    assertEquals(expectedEvent, takenEvent);
  }
}