package org.corejava.threads;

import java.util.Arrays;

public class ParallelSetAllExample
{
    public static void main(String[] args) {
        int[] array = new int[10];

        Arrays.parallelSetAll(array, index -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " -> Index: " + index);
            return index;
        });

        System.out.println(Arrays.toString(array));
    }
}
