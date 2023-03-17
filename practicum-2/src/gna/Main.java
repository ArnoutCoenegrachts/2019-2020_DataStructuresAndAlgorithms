package gna;

import libpract.StdIn;
import libpract.PriorityFunc;


class Main
{
	public static void main( String[] args )
	{
		int N = StdIn.readInt();
		int[][] tiles = new int[N][N];
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tiles[i][j] = StdIn.readInt();
		
		Board initial = new Board(tiles);
        long t1 = System.nanoTime();
		if (!initial.isSolvable())
		{
			System.out.println("No solution possible");
		}
		else
		{
            Solver solver = new Solver(initial, PriorityFunc.HAMMING);
			for (Board board : solver.solution())
                System.out.println(board.toString());

			System.out.println("Minimum number of moves = " + Integer.toString(solver.solution().size() - 1));
		}
        long t2 = System.nanoTime();
        double t = (t2-t1) * Math.pow(10,-9);
        System.out.println("tijd in seconden: "+t);        
	}
}


