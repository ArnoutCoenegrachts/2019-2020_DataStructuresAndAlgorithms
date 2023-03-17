package gna;

import java.util.Collection;
import java.util.Arrays;
import java.util.*;
import java.util.ArrayList;

import libpract.PriorityFunc;

public class Board
{
	// construct a board from an N-by-N array of tiles
	public Board( int[][] tiles )
	{
        setTiles(deepCopy(tiles), null); 
        setSize();
        setNumberOfSteps(0);
	}

    public Board( int[][] tiles , Board previous)
	{
        setTiles(deepCopy(tiles), previous); 
        setSize();
        setNumberOfSteps(previous.getNumberOfSteps()+1);
	}

    public int[][] getTiles() {
        return this.tiles;
    }
    private void setTiles(int[][] tiles, Board previous) {
        this.tiles = tiles;
        this.previousBoard = previous;
    }
    private int[][] tiles;

    public int getSize() {
        return this.size;
    }
    private void setSize() {
        this.size = this.getTiles().length;
    }
    private int size;

    private Board previousBoard;
    public Board getPreviousBoard() {
        return this.previousBoard;
    }

    private void setNumberOfSteps(int n) {
        this.numberOfSteps = n;
    }
    public int getNumberOfSteps() {
        return numberOfSteps;
    }
    private int numberOfSteps;

    private int[][] deepCopy(int[][] tiles) {
        int N = tiles.length;
        int[][] newTiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newTiles[i][j] = tiles[i][j];
            }
        }
        return newTiles;
    }

	
	// return number of blocks out of place
	public int hamming()
	{
        int[][] tiles = this.getTiles();
        int n = this.getSize();
        int count = 0;
        int tegel;
        int solutionTile;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tegel = tiles[i][j];
                if (i == n-1 && j == n-1) {
                    solutionTile = 0;
                }
                else {
                    solutionTile = n*i+j+1;
                }
                if (tegel !=0 && tegel != solutionTile) {
                    count++;
                }
            }
        }
        return count;
	}

    public int getHammingPriority() {
        return (this.getNumberOfSteps() + this.hamming());
    }
	
	// return sum of Manhattan distances between blocks and goal
	public int manhattan()
	{
		int n = this.getSize();
        int[][] tiles = this.getTiles();
        int I;
        int J;
        int count = 0;
        int tegel;
        int solutionTile;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tegel = tiles[i][j];
                if (i == n-1 && j == n-1) {
                    solutionTile = 0;
                }
                else {
                    solutionTile = n*i+j+1;
                }
                if (tegel !=0 && tegel != solutionTile) {
                    I = (tegel-1)/n; //use integer division to find the row coordinate 
                    J = tegel - 1 - I*n; //reverse n*i+j+1 to find the column coordinate
                    count = count + Math.abs(i - I) + Math.abs(j - J);
                }
            }
        }
        return count;
	}

    public int getManhattanPriority() {
        return (this.getNumberOfSteps() + this.manhattan());
    }
	
	// Does this board equal y. Two boards are equal when they both were constructed
	// using tiles[][] arrays that contained the same values.
	@Override
	public boolean equals(Object y)
	{
		if ( !(y instanceof Board) )
			return false;

		Board other = (Board)y;
		return Arrays.deepEquals(tiles, other.tiles);
	}

	// Since we override equals(), we must also override hashCode(). When two objects are
	// equal according to equals() they must return the same hashCode. More info:
	// - http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java/27609#27609
	// - http://www.ibm.com/developerworks/library/j-jtp05273/
    @Override
    public int hashCode()
	{
		return Arrays.deepHashCode(tiles);
	}
	
    
	// return a Collection of all neighboring board positions
	public Collection<Board> neighbors()
	{
        int[] coord0 = find(0, getTiles());
        int x = coord0[0];
        int y = coord0[1];
        int n = this.getSize();
        Queue<int[]> aangrenzend = new LinkedList<int[]>();
        if (x-1 >= 0) {
            aangrenzend.add(new int[]{x-1, y});
        }
        if (x+1 < n) {
            aangrenzend.add(new int[]{x+1, y});
        }
        if (y-1 >= 0) {
            aangrenzend.add(new int[]{x, y-1});
        }
        if (y+1 < n) {
            aangrenzend.add(new int[]{x, y+1});
        }
        ArrayList<Board> buren = new ArrayList<Board>();
        int[] coord;
        int[][] newTiles;
        int[][] previousTiles;
        Board oud = this.getPreviousBoard();
        if (oud == null) {
            previousTiles = null;
        }
        else {
            previousTiles = oud.getTiles();
        }
        Board nieuw;
        int N = aangrenzend.size();
        for (int i = 0; i < N; i++) {
            coord = aangrenzend.remove();
            newTiles = exchange(coord0, coord, deepCopy(getTiles()));
            nieuw = new Board(newTiles, this);
            if (nieuw.equals(oud) == false) {
                buren.add(nieuw);
            }
        }
        return buren;
	}

    public static int[] find(int tile, int[][] tiles) {
        int[] coord = new int[2];
        int n = tiles.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == tile) {
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }
        return coord;
    }

	
	// return a string representation of the board
	public String toString()
	{
		int[][] tiles = this.getTiles();
        int n = this.getSize();
        String board = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    board = board + " " + String.format("%8s ", " ");
                }
                else
                    board = board + " " + String.format("%8s ", Integer.toString(tiles[i][j]));
                if (j == n-1) {
                    board = board + System.lineSeparator();
                }
            }
        }
        return board;
	}

	// is the initial board solvable? Note that the empty tile must
	// first be moved to its correct position.
	public boolean isSolvable()
	{
        int[][] grid = deepCopy(getTiles());
        int n = getSize();
        int[] coord = find(0, grid);
        int[] new_coord;
        while (coord[0] != n-1) {
            new_coord = new int[]{coord[0]+1, coord[1]};
            grid = exchange(coord, new_coord, grid);
            coord = new_coord;
        }
        while (coord[1] != n-1) {
            new_coord = new int[]{coord[0], coord[1]+1};
            grid = exchange(coord, new_coord, grid);
            coord = new_coord;
        }
        float breuk = 1;
        int[] row = toRow(grid);
        int N = row.length;
        
        for (int j = 1; j < N; j++) {
            for (int i = 1; i < j; i++) {
                breuk = breuk * ( findRow(j, row) - findRow(i, row) ) / ( j - i );
            }
        }
        if (breuk >= 0) {
            return true;
        }
        else
            return false;
	}

    private int[] toRow(int[][] tiles) { 
        int n = tiles.length;
        int size = n*n;
        int[] row = new int[size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                row[n*i+j] = tiles[i][j];
            }
        }
        return row;
    }

    private int findRow(int tile, int[] row) { 
        int coord = -1;
        int n = row.length;
        for (int i = 0; i < n; i++) {
            if (row[i] == tile) {
                coord = i;
                return coord;
            }
        }
        return coord;
    }
    
    private int[][] exchange(int[] coord1, int[] coord2, int[][] tiles) {
        int tile1 = tiles[ coord1[0] ][ coord1[1] ];
        int tile2 = tiles[ coord2[0] ][ coord2[1] ];
        int n = tiles.length;
        if (tile1 == 0 || tile2 == 0) {
            tiles[ coord1[0] ][ coord1[1] ] = tile2;
            tiles[ coord2[0] ][ coord2[1] ] = tile1;
        }
        return tiles;
    }
    
    public int hammingCompareTo(Board that) {
        if (this.getHammingPriority() > that.getHammingPriority()) return +1;
        if (this.getHammingPriority() < that.getHammingPriority()) return -1;
        return 0;
    }
    
    public int manhattanCompareTo(Board that) {
        if (this.getManhattanPriority() > that.getManhattanPriority()) return +1;
        if (this.getManhattanPriority() < that.getManhattanPriority()) return -1;
        return 0;
    }
}



