package yapp.buddycon.temp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

  @Test
  void add() {
    int result = Calculator.add(1, 2);
    assertEquals(3, result);
  }
}