package ru.itis.collections.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IncreasingArray<T> implements Iterable<T> {

    private int DEFAULT_CAP = 10;
    private int INC_VALUE = 2;
    private int capacity;
    private T[] array;
    private int cursor;

    public IncreasingArray(){
        capacity = DEFAULT_CAP;
        array = (T[]) new Object[DEFAULT_CAP];
        cursor = 0;
    }

    public IncreasingArray(int capacity){
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
        cursor = 0;
    }

    public IncreasingArray(T[] anArr){
        array = (T[]) new Object [anArr.length];
        array = Arrays.copyOf(anArr, anArr.length);
        capacity = anArr.length;
        cursor = capacity;
    }

    private void increase(int t){
        T[] newArr;
        if(capacity < Integer.MAX_VALUE/t) {
            newArr = (T[]) new Object[array.length * t];
            capacity *= t;
        }
        else{
            newArr = (T[]) new Object[array.length + 1];
            capacity ++;
        }
        System.arraycopy(array, 0, newArr, 0, array.length);
        array = newArr;
    }

    private boolean isAdd(){
        if(cursor == Integer.MAX_VALUE){
            return false;
        }
        if(cursor == capacity){
            increase(INC_VALUE);
            return true;
        }
        return true;
    }


    public void add(T el, int ind){
        if(isAdd() && ind <= cursor) {
            for (int i = cursor; i > ind; i--) {
                array[i] = array[i - 1];
            }
            array[ind] = el;
            cursor++;
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void remove(int ind){
        if(ind >= cursor){
            throw new IndexOutOfBoundsException();
        }
        for(int i = ind; i < array.length-1; i++){
            array[i] = array[i+1];
        }
        array[array.length-1] = null;
        cursor--;
    }

    public int searchEl(T el){
        for(int i = 0; i < cursor; i++){
            if(el.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    public void showAll(){
        for(int i = 0; i < cursor; i++){
            System.out.println(array[i]);
        }
    }

    public void clear(){
        for(int i = 0; i < cursor; i++){
            array[i] = null;
        }
        cursor = 0;
        capacity = 0;
    }

    public int size(){
        return capacity;
    }

    public T get(int ind){
        return array[ind];
    }

    public T[] getArray(){
        T[]newData =(T[]) new Object[cursor];
        System.arraycopy(array, 0, newData, 0, cursor);
        return newData;
    }

    public int getCursor(){
        return cursor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncreasingArray<?> that = (IncreasingArray<?>) o;
        boolean flag;
        for(int i = 0; i < cursor; i++){
            if(!array[i].equals(that.array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for(int i = 0; i < cursor; i++){
            result = 31 * array[i].hashCode();
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new IncArrayIterator<T>();
    }

    private class IncArrayIterator<T> implements Iterator<T> {

        private int cur = 0;

        @Override
        public boolean hasNext() {
            return cur < array.length;
        }

        @Override
        public T next() {
            if(cur < array.length){
                return (T) array[cur++];
            }
            throw new NoSuchElementException();
        }
    }
}
