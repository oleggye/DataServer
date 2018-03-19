package integrationTest.by.bsac.tcs.server.process.parser.impl.custom;

import by.bsac.tcs.server.process.parser.impl.Method;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class MethodTest {


  @Test
  public void testLoadMethodEnum() {
    long expectedCount = 5;
    long actualCount = Arrays.stream(Method.values()).count();

    Assert.assertEquals(expectedCount, actualCount);
  }
}