package gna;

import java.util.Collection;
import java.util.List;
import libpract.PriorityFunc;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;
import java.util.*;

/**
 * A number of JUnit tests for Solver.
 *
 * Feel free to modify these to automatically test puzzles or other functionality
 */
public class UnitTests {
    
    int[][] correctTiles = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
    int[][] initialTiles = { {1, 2, 3}, {8, 0, 5}, {4, 7, 6} };
    int[][] otherTiles = { {7, 8, 5}, {4, 0, 2}, {3, 6, 1} };
    int[][] impossibleTiles = {{1,2,3},{5,4,6},{7,8,0}};
    int[][] fourTiles = { {0, 1, 6, 3}, {5, 2, 10, 4}, {9, 14, 12, 8}, {7, 13, 11, 15} };
    
    Board correctBoard = new Board(correctTiles);
    Board initialBoard = new Board(initialTiles);
    Board otherBoard = new Board(otherTiles);
    Board fourBoard = new Board(fourTiles);
    Board impossibleBoard = new Board(impossibleTiles);
    
    @Test
    public void testHammingSolver() {
        Solver solver = new Solver(initialBoard, PriorityFunc.HAMMING);
        Board solution = solver.getEnd();
        assertEquals(correctTiles, solution.getTiles());
        assertEquals(6, solution.getNumberOfSteps());
    }
    
    @Test
    public void testManhattanSolver() {
        Solver solver = new Solver(initialBoard, PriorityFunc.MANHATTAN);
        Board solution = solver.getEnd();
        assertEquals(correctTiles, solution.getTiles());
        assertEquals(6, solution.getNumberOfSteps());
    }
    
    @Test
    public void testHammingSolver2() {
        Solver solver = new Solver(otherBoard, PriorityFunc.HAMMING);
        Board solution = solver.getEnd();
        assertEquals(correctTiles, solution.getTiles());
        assertEquals(28, solution.getNumberOfSteps());
    }
    
    @Test
    public void testManhattanSolver2() {
        Solver solver = new Solver(otherBoard, PriorityFunc.MANHATTAN);
        Board solution = solver.getEnd();
        assertEquals(correctTiles, solution.getTiles());
        assertEquals(28, solution.getNumberOfSteps());
    }

    @Test
    public void testFind() {
        int[] coord = Board.find(3, initialBoard.getTiles());
        assertEquals(coord[0], 0);
        assertEquals(coord[1], 2);
    }
    @Test
    public void testSolvable() {
        assertTrue( initialBoard.isSolvable() );
        assertFalse( impossibleBoard.isSolvable() );
    }
    
    @Test
    public void testHamming() {
        assertEquals(5, initialBoard.hamming());
        assertEquals(7, otherBoard.hamming());
        assertEquals(13, fourBoard.hamming());
    }
    @Test
    public void testManhattan() {
        assertEquals(6, initialBoard.manhattan());
        assertEquals(18, otherBoard.manhattan());
        assertEquals(18, fourBoard.manhattan());
    }
    
    @Test
    public void testNeighbours() {
        Collection<Board> burenAlgoritme = initialBoard.neighbors();
        Collection<Board> burenTheorie = new ArrayList<Board>();
        int[][] leftTiles = { {1, 2, 3}, {0, 8, 5}, {4, 7, 6} };
        Board leftBoard = new Board(leftTiles, initialBoard);
        burenTheorie.add(leftBoard);
        int[][] rightTiles = { {1, 2, 3}, {8, 5, 0}, {4, 7, 6} };
        Board rightBoard = new Board(rightTiles, initialBoard);
        burenTheorie.add(rightBoard);
        int[][] topTiles = { {1, 0, 3}, {8, 2, 5}, {4, 7, 6} };
        Board topBoard = new Board(topTiles, initialBoard);
        burenTheorie.add(topBoard);
        int[][] bottomTiles = { {1, 2, 3}, {8, 7, 5}, {4, 0, 6} };
        Board bottomBoard = new Board(bottomTiles, initialBoard);
        burenTheorie.add(bottomBoard);
        for (Board b: burenAlgoritme) {
            assertTrue(burenTheorie.contains(b));
        }
        
        Collection<Board> buren2 = leftBoard.neighbors();
        assertFalse(buren2.contains(initialBoard));
        
        Collection<Board> buren3 = fourBoard.neighbors();
        int[][] fourRightTiles = { {1, 0, 6, 3}, {5, 2, 10, 4}, {9, 14, 12, 8}, {7, 13, 11, 15} };
        Board fourRightBoard = new Board(fourRightTiles, fourBoard);
        int[][] fourBottomTiles = { {5, 1, 6, 3}, {0, 2, 10, 4}, {9, 14, 12, 8}, {7, 13, 11, 15} };
        Board fourBottomBoard = new Board(fourBottomTiles, fourBoard);
        assertTrue(buren3.size() == 2);
        assertTrue(buren3.contains(fourRightBoard));
        assertTrue(buren3.contains(fourBottomBoard));
        
    }
}
