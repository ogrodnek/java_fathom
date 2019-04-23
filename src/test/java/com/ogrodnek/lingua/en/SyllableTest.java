package com.ogrodnek.lingua.en;

import org.junit.Test;
import static org.junit.Assert.*;
import static com.ogrodnek.lingua.en.Syllable.*;

public final class SyllableTest {
  @Test
	public void testOne() {
    assertEquals(1, syllable("the"));
    assertEquals(1, syllable("a"));
    assertEquals(1, syllable("i"));        
    assertEquals(1, syllable("who"));
	}
  
  @Test
  public void testTwo() {
    assertEquals(2, syllable("hello"));
    assertEquals(2, syllable("okay"));
    assertEquals(2, syllable("super"));    
    assertEquals(2, syllable("restless"));        
  }
  
  @Test
  public void testThree() {
    assertEquals(3, syllable("fortunate"));
    assertEquals(3, syllable("lunatic"));    
  }
  
  @Test
  public void testFour() {
    assertEquals(4, syllable("interesting"));
    assertEquals(4, syllable("complicated"));
    assertEquals(4, syllable("rollercoaster"));
  }
}
