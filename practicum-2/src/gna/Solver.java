package gna;

import java.util.Collection;
import java.util.List;
import java.util.*;
import java.util.PriorityQueue;

import libpract.PriorityFunc;

public class Solver
{
	/**
	 * Finds a solution to the initial board.
	 *
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 */
	public Solver(Board initial, PriorityFunc priority)
	{
		this.begin = initial;
        PriorityQueue<Board> pq;
        
        // Use the given priority function (either PriorityFunc.HAMMING
		// or PriorityFunc.MANHATTAN) to solve the puzzle.
        if (priority == PriorityFunc.HAMMING) {
            pq = new PriorityQueue<Board>(new HammingComparator());
        }
        else if (priority == PriorityFunc.MANHATTAN) {
            pq = new PriorityQueue<Board>(new ManhattanComparator());
        }
        else {
            throw new IllegalArgumentException("Priority function not supported");
        }
        pq.add(initial);
        Board min = (Board) pq.poll();
        Collection<Board> neighbors;
        while (isEndGoal(min) == false) {
            neighbors = min.neighbors();
            for (Board b : neighbors) {
                pq.add(b);
            }
            min = (Board) pq.poll();
        }
        einde = min;
	}
	

    private boolean isEndGoal(Board bord) {
        int n = bord.getSize();
        int solutionTile;
        int[][] tiles = bord.getTiles();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n-1 && j == n-1) {
                    solutionTile = 0;
                }
                else {
                    solutionTile = n*i+j+1;
                }
                if (tiles[i][j] != solutionTile) {
                    return false;
                }
            }
        }
        return true;
    }

    
	/**
	 * Returns a List of board positions as the solution. It should contain the initial
	 * Board as well as the solution (if these are equal only one Board is returned).
	 */
	public List<Board> solution()
	{
		int n = this.einde.getNumberOfSteps();
        Board[] temp = new Board[n+1];
        Board huidig = einde;
        for (int i = n; i >= 0; i--) {
            temp[i] = huidig;
            huidig = huidig.getPreviousBoard();
        }
        List<Board> oplossing = Arrays.asList(temp);
        
        return oplossing;
	}

    private Board begin;
    private Board einde;
    public Board getEnd() {
        return this.einde;
    }
}


