package by.bsac.tcs.server.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EventTest {

  @Test
  public void shouldReturnRegistrationRequestTypeByTypeCodeZero() {
    final int typeCode = 0;
    final RequestType expectedRequestType = RequestType.REGISTRATION;

    getRequestTypeAndAssert(typeCode, expectedRequestType);
  }

  @Test
  public void shouldReturnKeepAliveRequestTypeByTypeCodeOne() {
    final int typeCode = 1;
    final RequestType expectedRequestType = RequestType.KEEP_ALIVE;

    getRequestTypeAndAssert(typeCode, expectedRequestType);
  }

  @Test
  public void shouldReturnHasOpenedRequestTypeByTypeCodeTwo() {
    final int typeCode = 2;
    final RequestType expectedRequestType = RequestType.HAS_OPENED;

    getRequestTypeAndAssert(typeCode, expectedRequestType);
  }

  @Test
  public void shouldReturnHasClosedRequestTypeByTypeCodeThree() {
    final int typeCode = 3;
    final RequestType expectedRequestType = RequestType.HAS_CLOSED;

    getRequestTypeAndAssert(typeCode, expectedRequestType);
  }

  @Test
  public void shouldReturnHasClosedRequestTypeByTypeCodeTwo() {
    final int typeCode = 4;
    final RequestType expectedRequestType = RequestType.STATE_CHANGED;

    getRequestTypeAndAssert(typeCode, expectedRequestType);
  }

  private void getRequestTypeAndAssert(final int typeCode, final RequestType expectedRequestType) {
    final RequestType takenRequestType = RequestType.getRequestType(typeCode);
    assertEquals(expectedRequestType, takenRequestType);
  }
}