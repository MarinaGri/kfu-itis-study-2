package ru.itis.algorithms;

public class MergeSort2 {
    static void merge(int[] a, int f, int m, int l) {
        int t = m - f + 1;
        int[] b = new int[t];
        for (int i = 0; i < t; i++) {
            b[i] = a[f + i];
        }
        int[] c = new int[l - m];
        for (int i = 0; i < l - m; i++) {
            c[i] = a[m + i + 1];
        }
        int cur1 = 0;
        int cur2 = 0;
        int j = 0;

        while (cur1 < t && cur2 < l - m) {
            if (b[cur1] < c[cur2]) {
                a[j] = b[cur1++];
            } else {
                a[j] = c[cur2++];
            }
            j++;
        }
        if (cur1 < (t)) {
            for (int i = cur1; i < t; i++) {
                a[j] = b[i];
                j++;
            }
            if (cur2 < l - m) {
                for (int i = cur2; i < l - m; i++) {
                    a[j] = c[i];
                    j++;
                }
            }
        }
    }
    void sort(int[] a, int f, int l){
        if(f == l) {
            return;
        }
        int m = (l+f)/2;
        sort(a,f, m);
        sort(a,m+1,l);
        merge(a, f, m,l);
    }
}
