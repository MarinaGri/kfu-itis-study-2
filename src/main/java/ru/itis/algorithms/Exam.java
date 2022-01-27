package ru.itis.algorithms;

import java.util.Scanner;

public class Exam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = scanner.nextInt();
        }
        int t, p, i;
        t = p = -1;
        i = 0;
        boolean flag = true;
        while(i < n && flag) {
            for(int j = Math.max(i-k, 0); j <= Math.min(i+k, n-1); j++){
                int m = Math.abs(nums[i] - nums[j]);
                if(i != j && m <= r && m >= l){
                    t = ++i;
                    p = ++j;
                    flag = false;
                    break;
                }
            }
            i++;
        }
        System.out.println(t < p ? t + " " + p: p + " " + t);
    }
}
