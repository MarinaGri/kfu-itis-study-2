package ru.itis.comparators;

import java.util.Comparator;

public class ITEntrantAlphabetComparator implements Comparator<ITInstituteEntrant> {
    @Override
    public int compare(ITInstituteEntrant o1, ITInstituteEntrant o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();
        int l = Math.min(name1.length(), name2.length());
        for(int i = 0; i < l; i++){
            if(name1.charAt(i) > name2.charAt(i)) return 1;
            else if(name1.charAt(i) < name2.charAt(i)) return -1;
        }
        return 0;
    }
}
