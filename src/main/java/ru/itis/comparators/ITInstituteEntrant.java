package ru.itis.comparators;

public class ITInstituteEntrant implements Comparable<ITInstituteEntrant>{
    private String name;
    private int mathPoints;
    private int infPoints;
    private int ruPoints;

    public ITInstituteEntrant(String name, int mathPoints, int infPoints, int ruPoints) {
        setName(name);
        setMathPoints(mathPoints);
        setInfPoints(infPoints);
        setRuPoints(ruPoints);
    }

    @Override
    public int compareTo(ITInstituteEntrant o) {
        int s1 = this.infPoints + this.mathPoints + this.ruPoints;
        int s2 = o.infPoints + o.mathPoints + o.ruPoints;
        if(s1 > s2) return 1;
        else if(s1 < s2) return -1;
        else if(this.mathPoints > o.mathPoints) return 1;
        else if(this.mathPoints < o.mathPoints) return -1;
        else return Integer.compare(this.infPoints, o.infPoints);
    }

    @Override
    public String toString() {
        return name + " : math(" + mathPoints + "), inf(" + infPoints + "), ru(" + ruPoints + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ITInstituteEntrant that = (ITInstituteEntrant) o;
        return mathPoints == that.mathPoints &&
                infPoints == that.infPoints &&
                ruPoints == that.ruPoints;
    }

    @Override
    public int hashCode() {
        return 11 * mathPoints + 31 * infPoints + 53 * ruPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    public int getMathPoints() {
        return mathPoints;
    }

    public void setMathPoints(int mathPoints) {
        if(mathPoints >= 0) {
            this.mathPoints = mathPoints;
        }
    }

    public int getInfPoints() {
        return infPoints;
    }

    public void setInfPoints(int infPoints) {
        if(infPoints >= 0) {
            this.infPoints = infPoints;
        }
    }

    public int getRuPoints() {
        return ruPoints;
    }

    public void setRuPoints(int ruPoints) {
        if(ruPoints >= 0) {
            this.ruPoints = ruPoints;
        }
    }
}
