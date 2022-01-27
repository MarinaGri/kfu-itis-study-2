package ru.itis.algorithms;

public class MergeSort {
    public static void merge(int[] a, int first, int middle, int last){
        int i = first;
        int j = middle+1;
        int r = first;
        int[] c = new int[last+1];
        while (i <= middle && j <= last){
            if(a[i] < a[j]){
                c[r] = a[i];
                i++;
            }
            else{
                c[r] = a[j];
                j++;
            }
            r++;
        }
        while(i <= middle){
            c[r] = a[i];
            i++; r++;
        }
        while(j <= last){
            c[r]=a[j];
            j++; r++;
        }
        for(int h = first; h <= last; h++){
            a[h] = c[h];
        }
    }
    public static void sort(int[] a, int first, int last){
        if(first >= last) {
            return;
        }
        int middle = (first + last)/2;

        sort(a,first, middle);
        sort(a,middle+1,last);
        merge(a, first, middle,last);
    }
}
