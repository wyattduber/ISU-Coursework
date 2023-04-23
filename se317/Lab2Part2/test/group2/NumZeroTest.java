// Introduction to Software Testing
// Authors: Paul Ammann & Jeff Offutt
// Chapter 1; page ??
// JUnit tests for NumZero.java 

package group2;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class NumZeroTest
{
   @Test public void nonZeroFirstElement() 
   {
      int arr[] = {2, 7, 0};
      assertEquals("Nonzero in first element", 1, NumZero.numZero(arr));
   }

   @Test
   public void proofThatCodeIsBroken() {
      int arr[] = {0, 1, 2, 1, 0};
      assertEquals("The first element is not counted", 2, NumZero.numZero(arr));
   }
   
}
