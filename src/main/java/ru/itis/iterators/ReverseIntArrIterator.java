package ru.itis.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReverseIntArrIterator implements Iterator<Integer> {
    private Integer[] arr;
    private int cur;

    public ReverseIntArrIterator(Integer[] arr) {
        this.arr = arr;
        cur = arr.length-1;
    }

    @Override
    public boolean hasNext() {
        return cur != -1;
    }

    @Override
    public Integer next() {
        if(cur >= 0){
            return arr[cur--];
        }
        throw new NoSuchElementException();
    }
}
