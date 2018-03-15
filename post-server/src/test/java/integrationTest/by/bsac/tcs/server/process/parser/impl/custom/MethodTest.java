package integrationTest.by.bsac.tcs.server.process.parser.impl.custom;

import by.bsac.tcs.server.process.parser.impl.Method;
import java.util.Arrays;
import org.junit.Test;

public class MethodTest {


  @Test
  public void testLoadMethodEnum() {
    Arrays.stream(Method.values()).forEach(System.out::println);
  }
}