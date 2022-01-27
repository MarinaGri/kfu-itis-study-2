
import ru.itis.collections.set.MySortedSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MySortedSetTest {
    SortedSet<Integer> sortedSet;

    @Before
    public void createSortedSet(){
        sortedSet = new MySortedSet<>(Integer::compareTo);
    }

    @Test
    public void shouldAddOneElement(){
        sortedSet.add(9);
        sortedSet.add(9);
        sortedSet.add(9);
        Assert.assertEquals(sortedSet.size(),1);
    }

    @Test
    public void shouldReturnCorrectSubset(){
        SortedSet<Integer> s = new MySortedSet<>(Integer::compareTo);
        sortedSet.add(0);
        for(int i = 1; i < 7; i++){
            sortedSet.add(i);
            s.add(i);
        }
        sortedSet.add(7);
        sortedSet.add(8);
        Assert.assertEquals(s, sortedSet.subSet(1, 7));
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptNullInHeadSet(){
        for(int i = 1; i < 7; i++){
            sortedSet.add(i);
        }
        sortedSet.headSet(null);
    }

    @Test
    public void shouldDeleteAllColEl(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            sortedSet.add(i);
            arrayList.add(i);
        }
        sortedSet.removeAll(arrayList);
        Assert.assertTrue(sortedSet.isEmpty());
    }

    @Test
    public void shouldReturnFirstElement(){
        for(int i = 1; i < 7; i++){
            sortedSet.add(i);
        }
        Assert.assertEquals((Integer) 1, sortedSet.first());
    }

    @Test
    public void shouldContainAddedElement(){
        sortedSet.add(9);
        Assert.assertTrue(sortedSet.contains(9));
    }

    @Test
    public void shouldReturnCorrectArray(){
        for(int i = 0; i < 5; i++){
            sortedSet.add(i);
        }
        Integer[] arr = {0, 1, 2, 3, 4};
        Assert.assertEquals(arr, sortedSet.toArray());
    }

    @Test
    public void shouldBeSorted(){
        for(int i = 30; i > 0; i-=2){
            sortedSet.add(i);
        }
        for(int i = 0; i < sortedSet.size(); i++){
            int temp = sortedSet.first();
            sortedSet.remove(temp);
            Assert.assertTrue(temp < sortedSet.first());
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExIfEmptyAndCallLast(){
        sortedSet.last();
    }

    @Test
    public void shouldContainNoDuplicates(){
        for(int i = 30; i > 0; i-=2){
            sortedSet.add(i);
            sortedSet.add(i);
        }
        for(int i = 0; i < sortedSet.size(); i++){
            int temp = sortedSet.first();
            sortedSet.remove(temp);
            Assert.assertTrue(temp != sortedSet.first());
        }
    }

    @Test(expected = ClassCastException.class)
    public void shouldNotCompareIncompTypeCol(){
        sortedSet.add(0);
        TreeSet<String> m = new TreeSet<>();
        m.add("hi");
        sortedSet.containsAll(m);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldChekCorrectBoundariesInSubSet(){
        sortedSet.add(0);
        sortedSet.subSet(10, -8);
    }

    @Test(expected = ArrayStoreException.class)
    public void shouldCheckTypeOfArray(){
        sortedSet.add(0);
        String[] strings = {"a", "b"};
        sortedSet.toArray(strings);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldFollowRulesOfIteration(){
        for(int i  = 0; i < 10; i++){
            sortedSet.add(i);
        }
        Iterator<Integer> t = sortedSet.iterator();
        while (t.hasNext()){
            sortedSet.remove(5);
            t.next();
            t.remove();
        }
    }

    @Test
    public void shouldContainColElWithoutDuplicates(){
        List<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            arrayList.add(i);
            arrayList.add(i);
        }
        SortedSet<Integer> sortedSet2 = new MySortedSet<>(Integer::compareTo, arrayList);
        for(int i = 0; i < sortedSet2.size(); i++){
            int temp = sortedSet2.first();
            sortedSet2.remove(temp);
            Assert.assertTrue(temp != sortedSet2.first());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldChekCorrectBoundariesInTailSet(){
        sortedSet.add(0);
        sortedSet.tailSet(10);
    }

    @Test
    public void Get(){
        ArrayList<Integer> list = new ArrayList<>(1);
        list.add(9);
        Assert.assertEquals(8, (int) list.get(0));
    }
}

