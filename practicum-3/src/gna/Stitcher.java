package gna;

import java.util.List;
import libpract.*;
import java.util.PriorityQueue;
import java.util.*;

/**
 * Implement the methods stitch, seam and floodfill.
 */
public class Stitcher
{
	/**
	 * Return the sequence of positions on the seam. The first position in the
	 * sequence is (0, 0) and the last is (width - 1, height - 1). Each position
	 * on the seam must be adjacent to its predecessor and successor (if any).
	 * Positions that are diagonally adjacent are considered adjacent.
	 * 
	 * image1 and image2 are both non-null and have equal dimensions.
	 *
	 * Remark: Here we use the default computer graphics coordinate system,
	 *   illustrated in the following image:
	 * 
	 *        +-------------> X
	 *        |  +---+---+
	 *        |  | A | B |
	 *        |  +---+---+
	 *        |  | C | D |
	 *        |  +---+---+
	 *      Y v 
	 * 
	 *   The historical reasons behind using this layout is explained on the following
	 *   website: http://programarcadegames.com/index.php?chapter=introduction_to_graphics
	 * 
	 *   Position (y, x) corresponds to the pixels image1[y][x] and image2[y][x]. This
	 *   convention also means that, when an automated test mentioned that it used the array
	 *   {{A,B},{C,D}} as a test image, this corresponds to the image layout as shown in
	 *   the illustration above. 
	 */
	public List<Position> seam(int[][] image1, int[][] image2) {
        int row = image1.length;
        int column = image1[0].length;
        setWeights(image1, image2);
        distances = new double[row][column];
        for (double[] i: distances) {
            Arrays.fill(i, Double.POSITIVE_INFINITY);
        }
        distances[0][0] = weights[0][0];
        edgeTo = new Edge[row][column];
        
        Position start = new Position(0,0);
        Position finish = new Position(row-1, column-1);
        
        pq = new PriorityQueue<Position>(new PositionComparator());
        pq.add(start);
        while (pq.size()>0) {
            Position v = pq.poll();
            for (Edge e : neighbours(v, row, column)) {
                relax(e);
            }            
        }
        Stack<Position> path = new Stack<Position>();
        for (Edge e = getEdgeTo(finish); e != null; e = getEdgeTo(e.getStart())) {
            path.push(e.getEnd());
        }
        path.push(start);
        List<Position> seam = new ArrayList<Position>();
        while (!path.empty()) {
            seam.add(path.pop());
        }
        return seam;
        
	}

    public void setWeights(int[][] image1, int[][] image2) {
        int row = image1.length;
        int column = image1[0].length;
        int[][] distances = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                distances[i][j] = ImageCompositor.pixelSqDistance(image1[i][j], image2[i][j]);
            }
        }
        this.weights = distances;
    }
    public int weightOf(Position p) {
        return this.weights[p.getY()][p.getX()];
    }
    public int[][] weights; 

    public void setDistanceTo(Position p, double newDistance) {
        this.distances[p.getY()][p.getX()] = newDistance;
    }
    public double distanceTo(Position p) {
        return this.distances[p.getY()][p.getX()];
    }
    public static double[][] distances;

    public Edge getEdgeTo(Position p) {
        return this.edgeTo[p.getY()][p.getX()];
    }
    public void setEdgeTo(Position end, Edge e) {
        this.edgeTo[end.getY()][end.getX()] = e;
    }
    private Edge[][] edgeTo;

    PriorityQueue<Position> pq;

    public void relax(Edge e) {
        Position start = e.getStart();
        Position end = e.getEnd();
        int weight = e.getWeight();
        double distanceStart = distanceTo(start);
        int weightEnd = weightOf(end);
        if (distanceTo(end) > distanceStart + weightEnd) {
            setDistanceTo(end, distanceStart + weightEnd);
            setEdgeTo(end, e);
            if (!pq.contains(end)) pq.add(end);
        }
    }
           
    public List<Edge> neighbours(Position current, int row, int column) {
        int x = current.getX();
        int y = current.getY();
        List<Edge> buren = new ArrayList<Edge>();
        Position temp;
        if (y > 0) {
            temp = new Position(y-1, x);
            buren.add( new Edge(current, temp, weightOf(temp) ) );
            if (x > 0) {
                temp = new Position(y-1, x-1);
                buren.add( new Edge(current, temp, weightOf(temp) ) );
            }
            if (x < column-1) {
                temp = new Position(y-1, x+1);
                buren.add( new Edge(current, temp, weightOf(temp) ) );
            }
        }
        if (y < row-1) {
            temp = new Position(y+1, x);
            buren.add( new Edge(current, temp, weightOf(temp) ) );
            if (x > 0) {
                temp = new Position(y+1, x-1);
                buren.add( new Edge(current, temp, weightOf(temp) ) );
            }
            if (x < column-1) {
                temp = new Position(y+1, x+1);
                buren.add( new Edge(current, temp, weightOf(temp) ) );
            }
        }
        if (x > 0) {
            temp = new Position(y, x-1);
            buren.add( new Edge(current, temp, weightOf(temp) ) );
        }
        if (x < column-1) {
            temp = new Position(y, x+1);
            buren.add( new Edge(current, temp, weightOf(temp) ) );
        }
        return buren;
    }       

    public static int positionCompare(Position a, Position b) {
        if (distances[a.getY()][a.getX()] > distances[b.getY()][b.getX()]) return +1;
        if (distances[a.getY()][a.getX()] < distances[b.getY()][b.getX()]) return -1;
        return 0;
    }

    

	/**
	 * Apply the floodfill algorithm described in the assignment to mask. You can assume the mask
	 * contains a seam from the upper left corner to the bottom right corner. The seam is represented
	 * using Stitch.SEAM and all other positions contain the default value Stitch.EMPTY. So your
	 * algorithm must replace all Stitch.EMPTY values with either Stitch.IMAGE1 or Stitch.IMAGE2.
	 *
	 * Positions left to the seam should contain Stitch.IMAGE1, and those right to the seam
	 * should contain Stitch.IMAGE2. You can run `ant test` for a basic (but not complete) test
	 * to check whether your implementation does this properly.
	 */
	public void floodfill(Stitch[][] mask) {
		//throw new RuntimeException("not implemented yet");
        
        int row = mask.length;
        int column = mask[0].length;
        filler = Stitch.IMAGE1;
        /* These booleans will register whether the SEAM has entered the current row from one direction (the name of the relevant boolean), but hasn't left yet in the opposite direction */
        boolean up = false;
        boolean down = false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //reset values at the start of a new row
                if (j == 0) {
                    filler = Stitch.IMAGE1;
                    if (i == 0) up = true;
                    else up = false;
                    down = false;
                }
                if (mask[i][j] != Stitch.SEAM) {
                    mask[i][j] = filler;
                }
                else { 
                    /*
                    When the current pixel is part of the seam, we need to check if there are seam-tiles above and below the current tile. If there are, it means that the seam passes through this row and that the filler needs to be changed. If there are at least one seal-tile above, but none below, then there are 2 options. Or the seam enters the row from above and leaves in the same direction, which means that the filler does not have to change, or the seam continues in this row leaves in any direction at a further point in this row. If there are at least one seal-tile below, but none above, there are similar options.
                    */
                    if (checkAbove(mask, i, j)) {
                        if (checkBelow(mask, i, j) || down == true){
                            switchFiller();
                            down = false;
                        }
                        else if (j != column-1) {
                            if (mask[i][j+1] == Stitch.SEAM) {
                                up = true;
                            }
                        }
                    }
                    else if (checkBelow(mask, i, j)) {
                        if (up == true){
                            switchFiller();
                            up = false;
                        }
                        else if (j != column-1) {
                            if (mask[i][j+1] == Stitch.SEAM) {
                                down = true;
                            }
                        }
                    }
                }
            }
        }   
	}
    /*
    This method checks if there are any SEAM-tiles that are both adjecent to and below the tile with coordinates (x,y) = (j,i)
    */
    public boolean checkBelow(Stitch[][] mask, int i, int j) {
        if (i == mask.length-1) return false; //cannot go any lower
        else if (mask[0].length == 1) return true;
        else if (j == 0) {
            if (mask[i+1][j] == Stitch.SEAM || mask[i+1][j+1] == Stitch.SEAM) return true;
            else return false;
        }
        else if (j == mask[0].length-1) {
            if (mask[i+1][j-1] == Stitch.SEAM || mask[i+1][j] == Stitch.SEAM) return true;
            else return false;
        }
        else if (mask[i+1][j-1] == Stitch.SEAM || mask[i+1][j] == Stitch.SEAM || mask[i+1][j+1] == Stitch.SEAM) return true;
        else return false;
    }
    /*
    This method checks if there are any SEAM-tiles that are both adjecent to and above the tile with coordinates (x,y) = (j,i)
    */
    public boolean checkAbove(Stitch[][] mask, int i, int j) {
        if (i == 0) return false; //cannot go any higher
        else if (mask[0].length == 1) return true;
        if (j == 0) {
            if (mask[i-1][j] == Stitch.SEAM || mask[i-1][j+1] == Stitch.SEAM) return true;
            else return false;
        }
        else if (j == mask[0].length-1) {
            if (mask[i-1][j-1] == Stitch.SEAM || mask[i-1][j] == Stitch.SEAM) return true;
            else return false;
        }
        else if (mask[i-1][j-1] == Stitch.SEAM || mask[i-1][j] == Stitch.SEAM || mask[i-1][j+1] == Stitch.SEAM) return true;
        else return false;
    }

    /*
    This method changes which Image is currently being used by the method floodfill to color the tiles.
    */
    public void switchFiller() {
        if (filler == Stitch.IMAGE1) {
            filler = Stitch.IMAGE2;
        }
        else {
            filler = Stitch.IMAGE1;
        }
    }
    private Stitch filler;
    
    /*
    This method only exists to show a mask during testing for easy checking if the floodfill method works.
    */
    public static String toString(Stitch[][] mask) {
        int row = mask.length;
        int column = mask[0].length;
        String str = "";
        Stitch current;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                current = mask[i][j];
                if (current == Stitch.SEAM) str = str +" X ";
                else if (current == Stitch.IMAGE1) str = str+" - ";
                else if (current == Stitch.IMAGE2) str = str+" + ";
                else str = str+" ? ";
                if (j == column-1) str = str + System.lineSeparator();
            }
        }
        return str;
    }


	/**
	 * Return the mask to stitch two images together. The seam runs from the upper
	 * left to the lower right corner, where in general the rightmost part comes from
	 * the second image (but remember that the seam can be complex, see the spiral example
	 * in the assignment). A pixel in the mask is Stitch.IMAGE1 on the places where
	 * image1 should be used, and Stitch.IMAGE2 where image2 should be used. On the seam
	 * record a value of Stitch.SEAM.
	 * 
	 * ImageCompositor will only call this method (not seam and floodfill) to
	 * stitch two images.
	 * 
	 * image1 and image2 are both non-null and have equal dimensions.
	 */
	public Stitch[][] stitch(int[][] image1, int[][] image2) {
        List<Position> seam = seam(image1, image2);
        
        int row = image1.length;
        int column = image1[0].length;
        Stitch[][] mask = new Stitch[row][column];
        for (Position p : seam) {
            mask[p.getY()][p.getX()] = Stitch.SEAM;
        }
        floodfill(mask);
        return mask;
	}
}


