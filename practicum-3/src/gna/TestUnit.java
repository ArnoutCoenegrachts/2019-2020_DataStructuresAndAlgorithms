package gna;

import java.util.List;
import libpract.*;

import static org.junit.Assert.*;
import org.junit.Test;


import java.awt.Color;

/**
 * A number of JUnit tests for Solver.
 *
 * Feel free to modify these to automatically test puzzles or other functionality
 */
public class TestUnit {
    
    @Test
    public void testFloodFill1() {
        Stitcher testStitcher = new Stitcher();
        Stitch[][] test = new Stitch[][]{ { Stitch.SEAM,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY},
                                          {Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY},
                                          {Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM,  Stitch.SEAM, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM}
                                        };
        testStitcher.floodfill(test);
        System.out.println(Stitcher.toString(test));
        
    }
    @Test
    public void testFloodFill2() {
        Stitcher testStitcher = new Stitcher();
        Stitch[][] test = new Stitch[][]{ { Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY},
                                          { Stitch.SEAM, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY},
                                          {Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM, Stitch.EMPTY},
                                          {Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM}
                                        }; 
        testStitcher.floodfill(test);
        System.out.println(Stitcher.toString(test));
        
    }
    @Test
    public void testFloodFill3() {
        Stitcher testStitcher = new Stitcher();
        Stitch[][] test = new Stitch[][]{ 
            { Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY,
             Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY},
            { Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY,
             Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY},
            { Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY,
             Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY},
            {Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY,
             Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY},
            {Stitch.EMPTY,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM, Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY,  Stitch.SEAM,
              Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM},
            {Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM, Stitch.EMPTY,  Stitch.SEAM,
             Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM},
            {Stitch.EMPTY,  Stitch.SEAM, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM,
             Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM},
            {Stitch.EMPTY,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM,  Stitch.SEAM,
             Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM},
            {Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY , Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY, Stitch.EMPTY,
             Stitch.EMPTY, Stitch.EMPTY,  Stitch.SEAM}
                                        }; 
        testStitcher.floodfill(test);
        System.out.println(Stitcher.toString(test));
        
    }
    
    @Test
    public void testSeam1() {
        int[][] image1 = new int[][] { {    1, 10000, 10000, 10000}, 
                                       {    1,     1, 10000, 10000}, 
                                       {10000,     1, 10000, 10000}, 
                                       {10000, 10000,     1,     1}};
        int[][] image2 = new int[][] { {    0,     0,     0,     0}, 
                                       {    0,     0,     0,     0}, 
                                       {    0,     0,     0,     0}, 
                                       {    0,     0,     0,     0}};
        Stitcher testStitcher = new Stitcher();
        List<Position> test = testStitcher.seam(image1, image2);
        assertEquals(5, test.size());
    }
    
    @Test
    public void testStitch1() {
        int[][] image1 = new int[][] { {    1, 10000, 10000, 10000}, 
                                       {    1, 10000, 10000, 10000}, 
                                       {10000,     1, 10000, 10000}, 
                                       {10000, 10000,     1,     1}};
        int[][] image2 = new int[][] { {    0,     0,     0,     0}, 
                                       {    0,     0,     0,     0}, 
                                       {    0,     0,     0,     0}, 
                                       {    0,     0,     0,     0}};
        Stitcher testStitcher = new Stitcher();
        Stitch[][] mask = testStitcher.stitch(image1, image2);
        Stitch[][] controle = new Stitch[][] { {Stitch.SEAM  , Stitch.IMAGE2, Stitch.IMAGE2, Stitch.IMAGE2},  
                                               {Stitch.SEAM  , Stitch.IMAGE2, Stitch.IMAGE2, Stitch.IMAGE2}, 
                                               {Stitch.IMAGE1, Stitch.SEAM  , Stitch.IMAGE2, Stitch.IMAGE2}, 
                                               {Stitch.IMAGE1, Stitch.IMAGE1, Stitch.SEAM  , Stitch.SEAM  }
                                             };
        assertEquals(controle, mask);
    }
    
    @Test
    public void questionOneTest() {
        System.out.println("Question 1:");
        int[][] image1 = new int[][] {{ new Color(7, 0, 0).getRGB(), new Color(0, 1, 0).getRGB(), new Color(0, 0, 1).getRGB()}, 
                                      { new Color(2, 0, 0).getRGB(), new Color(0, 8, 0).getRGB(), new Color(0, 0, 5).getRGB()},
                                      { new Color(1, 0, 0).getRGB(), new Color(0, 1, 0).getRGB(), new Color(0, 0, 8).getRGB()},
                                     };
        int[][] image2 = new int[][] {{ new Color(0, 0, 0).getRGB(), new Color(0, 0, 0).getRGB(), new Color(0, 0, 0).getRGB()}, 
                                      { new Color(0, 0, 0).getRGB(), new Color(0, 0, 0).getRGB(), new Color(0, 0, 0).getRGB()},
                                      { new Color(0, 0, 0).getRGB(), new Color(0, 0, 0).getRGB(), new Color(0, 0, 0).getRGB()},
                                     };
        Stitcher testStitcher = new Stitcher();
        Stitch[][] mask = testStitcher.stitch(image1, image2);
        System.out.println(Stitcher.toString(mask));
    }
    
}
