package com.keviiweb.keips;

import java.util.Comparator;

public class SortTotalPoints implements Comparator<CCA> {
    
    public int compare(CCA a, CCA b) {
        
        //sort in descending order
        return b.getTotalPoints() - a.getTotalPoints();
    }
}
