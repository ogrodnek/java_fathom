package com.representqueens.lingua.en;

import org.junit.Assert;
import org.junit.Test;

import com.representqueens.lingua.en.Fathom.Stats;


public class FathomTest {
  @Test
  public void testSimple() {
    
    Stats s = Fathom.analyze("This is a sentence.  This is another sentence.");
    
    Assert.assertEquals(2, s.getNumSentences());
    Assert.assertEquals(1, s.getNumComplexWords());
    Assert.assertEquals(8, s.getNumWords());
    
    System.out.println(s);
  }
}
