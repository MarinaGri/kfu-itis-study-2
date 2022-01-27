package ru.itis.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericsIterator<T> implements Iterator<T> {

    private T[]arr;
    private int cur;

    public GenericsIterator(T[] arr) {
        this.arr = arr;
        cur = 0;
    }

    @Override
    public boolean hasNext() {
        return cur < arr.length;
    }

    @Override
    public T next() {
        if(cur < arr.length){
            return arr[cur++];
        }
        throw new NoSuchElementException();
    }
}
