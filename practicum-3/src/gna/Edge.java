package gna;

import libpract.*;

public class Edge {
    public Edge(Position start, Position end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    
    private Position start;
    private Position end;
    private int weight;
    
    public Position getStart() {
        return this.start;
    }
    public Position getEnd() {
        return this.end;
    }
    public int getWeight() {
        return this.weight;
    }
}
