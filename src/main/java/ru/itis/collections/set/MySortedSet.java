package ru.itis.collections.set;

import java.util.*;

public class MySortedSet<T> extends AbstractSet<T> implements SortedSet<T> {

    private IncreasingArray<T> data;
    private Comparator<? super T> comparator;

    public MySortedSet(Comparator<? super T> comparator){
        this.comparator = comparator;
        data = new IncreasingArray<>();
    }

    public MySortedSet(Comparator<? super T> comparator, Collection<? extends T> col){
        this.comparator = comparator;
        data = new IncreasingArray<>();
        T[]newData =(T[]) new Object[col.size()];
        int size = 0;
        for(T el : col){
            newData[size] = el;
            size++;
        }
        if(!isDuplicates(newData, newData.length)) {
            data = new IncreasingArray<>(sort(newData, newData.length));
        }
        else addAll(col);
    }

    private boolean isDuplicates(T[] arr, int size){
        for(int i = 0; i < size - 1; i++){
            for(int j = i+1; j < size; j++){
                if (arr[i].equals(arr[j])) {
                        return true;
                }
            }
        }
        return false;
    }

    private T[] sort(T[] arr, int size){
        for(int i = size - 1; i > -1; i--){
            for(int j = 0; j < i; j++){
                if(comparator.compare(arr[j], arr[j+1]) > 0){
                    T t = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = t;
                }
            }
        }
        return arr;
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        int firstInd = searchIndexForEl(fromElement);
        int lastInd = searchIndexForEl(toElement);
        if((firstInd > lastInd) || firstInd >= size()) {
            throw new IllegalArgumentException();
        }
        T[]newArr =(T[]) new Object[lastInd - firstInd];
        int j = 0;
        for(int i = firstInd; i < lastInd; i++){
            newArr[j] = data.get(i);
            j++;
        }
        IncreasingArray<T> newData = new IncreasingArray<>(newArr);
        MySortedSet<T> newSet = new MySortedSet<>(comparator);
        newSet.data = newData;
        return newSet;
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return subSet(first(),toElement);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        MySortedSet<T> newSet = (MySortedSet<T>) subSet(fromElement,last());
        newSet.add(last());
        return newSet;
    }

    @Override
    public T first() {
        if(data.getCursor() == 0){
            throw new NoSuchElementException();
        }
        return data.get(0);
    }

    @Override
    public T last() {
        if(data.getCursor() == 0){
            throw new NoSuchElementException();
        }
        return data.get(data.getCursor() - 1);
    }

    @Override
    public int size() {
        return data.getCursor();
    }

    @Override
    public boolean isEmpty() {
        return data.getCursor() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if(!checkCompatibleTypes(o)) return false;
        T t = (T) o;
        return data.searchEl(t) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @Override
    public Object[] toArray() {
        return data.getArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if(a == null){
            throw new NullPointerException();
        }
        try{
            checkCompatibleTypes(a[0]);
        } catch (ClassCastException e) {
            throw new ArrayStoreException();
        }
        int l = data.getCursor();
        if(a.length >= l){
            System.arraycopy(data.getArray(), 0, a, 0,l );
            for(int i = l; i < a.length; i++){
                a[i] = null;
            }
            return a;
        }
        return (T[]) data.getArray();
    }

    @Override
    public boolean add(T t) {
        if(data.searchEl(t) != -1){
            return false;
        }
        data.add(t, searchIndexForEl(t));
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!checkCompatibleTypes(o)) return false;
        T t = (T)o;
        int ind = data.searchEl(t);
        if(ind == -1){
            return false;
        }
        data.remove(ind);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> col) {
        for(Object el : col) {
            if(!checkCompatibleTypes(el)) return false;
            T t = (T)el;
            if(data.searchEl(t) == -1){
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> col) {
        int cur = data.getCursor();
        for(T el : col){
            if(data.searchEl(el) == -1){
                data.add(el, searchIndexForEl(el));
            }
        }
        return cur != data.getCursor();
    }

    private int searchIndexForEl(T el){
        int i = 0;
        while (i < data.getCursor() && data.get(i) != null && comparator.compare(el, data.get(i)) > 0){
            i ++;
        }
        return i;
    }

    @Override
    public boolean retainAll(Collection<?> col) {
        int cur = data.getCursor();
        boolean isEquals = false;
        for(int i = 0; i < data.getCursor(); i++){
            for(Object el : col){
                if(!checkCompatibleTypes(el)) return false;
                if(data.get(i).equals(el)){
                    isEquals = true;
                }
            }
            if(!isEquals){
                data.remove(i);
                i--;
            }
            isEquals = false;
        }
        return cur != data.getCursor();
    }
    @Override
    public boolean removeAll(Collection<?> col) {
        int cur = data.getCursor();
        for(Object el : col){
            if(!checkCompatibleTypes(el)) return false;
            T t = (T)el;
            int ind = data.searchEl(t);
            if (ind != -1) {
                data.remove(ind);
            }
        }
        return cur != data.getCursor();
    }

    @Override
    public void clear() {
        data.clear();
    }

    private boolean checkCompatibleTypes(Object o) throws ClassCastException {
        boolean isInstance = false;
        try{
            isInstance = first().getClass().isInstance(o);
        } catch (NoSuchElementException ex) {
            return false;
        }
        if(!isInstance) throw new ClassCastException();
        return true;
    }
    
    @Override
    public String toString() {
        return "MySortedSet: " + Arrays.toString(toArray());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MySortedSet<?> set = (MySortedSet<?>) o;
        return Objects.equals(data, set.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }
}
