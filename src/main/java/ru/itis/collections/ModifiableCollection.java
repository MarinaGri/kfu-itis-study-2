package ru.itis.collections;

import java.util.*;

public class ModifiableCollection<T> extends AbstractCollection<T> {

    private final static int DEFAULT_SIZE = 10;

    private T[] data;
    private int size;
    private int cursor;

    public ModifiableCollection(){
        data =(T[]) new Object[DEFAULT_SIZE];
        size = DEFAULT_SIZE;
        cursor = 0;
    }

    public ModifiableCollection(Collection<? extends T> col){
        data =(T[]) new Object[col.size()];
        size = 0;
        for(T el : col){
            data[size] = el;
            size++;
        }
        cursor = size;
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicCollectionIterator<T>();
    }

    @Override
    public int size() {
        return cursor;
    }

    @Override
    public boolean add(T el){
        if(cursor >= data.length){
            T[] newData = (T[]) new Object [data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
            size *= 2;
        }
        data[cursor] = el;
        size ++;
        cursor++;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cursor; i++) {
            builder.append(data[i]);
        }
        return builder.toString();
    }

    private class BasicCollectionIterator<T> implements Iterator <T>{
        private int current = 0;
        private int ind = -1;
        private T lastValue;

        @Override
        public boolean hasNext() {
            return current < cursor;
        }

        @Override
        public T next() {
            if(current < cursor){
                lastValue = (T) data[current];
                ind = current;
                current++;
                return lastValue;
            }
            throw new NoSuchElementException();
        }
        public void remove(){
            if (ind == -1 || data[ind] == null || !data[ind].equals(lastValue)) {
                throw new IllegalStateException();
            }

            for(int i = ind; i < data.length-1; i++){
                data[i] = data [i+1];
            }
            data[data.length-1] = null;
            current--;
            cursor--;
        }
    }
}
