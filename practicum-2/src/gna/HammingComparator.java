package gna;

import java.util.Collection;
import java.util.List;
import java.util.*;

import libpract.PriorityFunc;

class HammingComparator implements Comparator<Board>
{
    public int compare(Board a, Board b) {
        return a.hammingCompareTo(b);
    }
        
}