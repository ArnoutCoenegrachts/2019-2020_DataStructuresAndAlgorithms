package gna;

import java.util.*;
import libpract.*;

class PositionComparator implements Comparator<Position> 
{
    public int compare(Position a, Position b) {
        return Stitcher.positionCompare(a,b);
    }
}