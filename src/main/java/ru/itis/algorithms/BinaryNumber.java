package ru.itis.algorithms;

import java.util.Arrays;

public class BinaryNumber {
    private boolean[] num;

    public BinaryNumber(int n){
        num = convertToBinary(n);
    }

    public BinaryNumber(boolean[] arr){
        if(arr != null) {
            num = arr;
        }
    }

    public boolean[] convertToBinary(int a){
        int n; //number length
        if(a == 0) {
            n = 1;
        }
        else n = (int)(Math.log(a)/Math.log(2)+1);

        boolean[] arr = new boolean[n];
        int i = 0;
        //digits of humber are written in reverse order (101110 -> 011101)
        while(a > 0){
            arr[i] = a % 2 != 0;
            a /= 2;
            i++;
        }
        return arr;
    }

    public int convertToDecimal(){
        int res = 0;
        for(int i = 0; i < num.length; i++){
            res += Math.pow(2,i) * (num[i] ? 1 : 0);
        }
        return res;
    }

    public BinaryNumber sum(BinaryNumber a){
        int max = Math.max(this.num.length, a.num.length);
        int min = Math.min(this.num.length, a.num.length);

        boolean[] c = this.num.length > a.num.length ? this.num : a.num;
        boolean[] s = new boolean[max+1];
        boolean one = false;  //"one in mind"

        for(int i = 0; i < min; i++){
            if(this.num[i] && a.num[i]) {
                s[i] = one;
                one = true;
            }
            else if(this.num[i] ^ a.num[i]){
                s[i] = !one;
            }
            else {
                s[i] = one;
                one = false;
            }
        }
        for(int i = min; i < max; i++){
            if(c[i] && one){
                s[i] = false;
            }
            else if(c[i] ^ one){
                s[i] = true;
                one = false;
            }
            else {
                s[i] = false;
            }
        }
        //if length of sum result is greater than length of both terms
        if(one) {
            s[s.length-1] = true;
            return new BinaryNumber(s);
        }
        else {
            boolean[] s2 = new boolean[s.length-1];
            System.arraycopy(s, 0, s2, 0, s2.length);
            return new BinaryNumber(s2);
        }
    }

    public BinaryNumber sub(BinaryNumber a){
        int max = this.num.length;
        int min = a.num.length;

        boolean[] c = this.num;
        boolean[] s = new boolean[max];

        for(int i = 0; i < min; i++) {
            if(!c[i] && !a.num[i] || c[i] && a.num[i]) {
                s[i] = false;
            }
            // 0 - 1 = 1 (and take one from higher digits)
            else if(!c[i] && a.num[i]) {
                s[i] = true;
                takeOne(i,c);
            }
            else if(c[i] && !a.num[i]){
                s[i] = true;
            }
        }
        if (max - min >= 0) {
            System.arraycopy(c, min, s, min, max - min);
        }

        if(s[s.length-1]) {
            return new BinaryNumber(s);
        }
        // removing insignificant zeros
        int i = s.length-1;
        int counter = 0;
        while(i > 0 && !s[i]){
            i--;
            counter++;
        }
        boolean[] s2 = new boolean[s.length-counter];
        if (s2.length >= 0) {
            System.arraycopy(s, 0, s2, 0, s2.length);
        }
        return new BinaryNumber(s2);
    }

    //takes one until it meets 1 and turns 1 to 0
    private void takeOne(int i, boolean[] a){
        while(i < a.length  && !a[i]){
            a[i] = !a[i];
            i++;
        }
        if(i < a.length) {
            a[i] = false;
        }
    }

    // for a number with digits in reverse order
    // copy elements to n and next positions
    public BinaryNumber leftBitShift(int n){
        boolean[] a = new boolean[num.length+n];
        System.arraycopy(num, 0, a, n, num.length);
        return new BinaryNumber(a);
    }

    // for a number with digits in reverse order
    // copy elements starting at n
    public BinaryNumber rightBitShift(int n){
        if(n > num.length){
            boolean[] b = { false };
            return new BinaryNumber(b);
        }
        boolean[] a = new boolean[num.length-n];
        System.arraycopy(num, n, a, 0, num.length-n);
        return new BinaryNumber(a);
    }

    public BinaryNumber multiply(BinaryNumber a){
        int n;
        boolean[] minArr;
        boolean[] maxArr;
        if(this.num.length > a.num.length){
            n = a.num.length;
            minArr = a.num;
            maxArr = this.num;
        }
        else {
            n = this.num.length;
            minArr = this.num;
            maxArr = a.num;
        }
        if(n == 1){
            // number * 1 = number
            if(minArr[0]) return new BinaryNumber(maxArr);
            // number * 0 = 0
            else {
                boolean[] p = { false };
                return new BinaryNumber(p);
            }
        }

        int m = n / 2;
        /*
        Example: x  = 101110, m = 3
                 x1 = 101
                 x2 = x - (x1 << m) = 101110 - (101 << 3) = 101110 - 101000 = 110
         */
        BinaryNumber x1 = this.rightBitShift(m);         //the first part of the first multiplier
        BinaryNumber x2 = this.sub(x1.leftBitShift(m));  //the second part
        BinaryNumber y1 = a.rightBitShift(m);
        BinaryNumber y2 = a.sub(y1.leftBitShift(m));

        BinaryNumber p1 = x1.multiply(y1);
        BinaryNumber p2 = x2.multiply(y2);
        BinaryNumber p3 = ((x1.sum(x2)).multiply((y1.sum(y2))));

        //(p1 << m*2) + ((p3 - p1 - p2) << m) + p2
        BinaryNumber r1 = p1.leftBitShift(m*2);
        BinaryNumber r2 = ((p3.sub(p1)).sub(p2)).leftBitShift(m);

        return (r1.sum(r2)).sum(p2);
    }

    @Override
    public String toString() {
        return "" + convertToDecimal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryNumber that = (BinaryNumber) o;
        return Arrays.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(num);
    }
}
