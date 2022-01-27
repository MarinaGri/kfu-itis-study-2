package ru.itis.iterators;

import java.util.Iterator;

public class RandomIterator implements Iterator {

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Object next() {
        return (int)(Math.random()* (Integer.MAX_VALUE))-Integer.MAX_VALUE/2;
    }
}
