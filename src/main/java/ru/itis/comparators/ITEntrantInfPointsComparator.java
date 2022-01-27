package ru.itis.comparators;

import java.util.Comparator;

public class ITEntrantInfPointsComparator implements Comparator<ITInstituteEntrant> {
    @Override
    public int compare(ITInstituteEntrant o1, ITInstituteEntrant o2) {
        return Integer.compare(o1.getInfPoints(), o2.getInfPoints());
    }
}
