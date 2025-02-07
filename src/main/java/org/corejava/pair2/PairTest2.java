package org.corejava.pair2;

import java.time.LocalDate;
import java.util.function.IntFunction;

import static java.lang.System.out;

public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays =
                {
                        LocalDate.of(1906, 12, 9),
                        LocalDate.of(1815, 12, 10),
                        LocalDate.of(1903, 12, 3),
                        LocalDate.of(1910, 6, 22),
                };
//        Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
//        out.println("min = " + mm.getFirst());
//        out.println("max = " + mm.getSecond());
        String[] names = ArrayAlg.minmax(String[]::new, "Tom", "Dice", "Harry");
        out.println("min = " + names[0]);
        out.println("max = " + names[1]);
    }
}
class ArrayAlg {
//    public static <T extends Comparable> Pair<T> minmax(T[] a)
//    {
//        if (a == null || a.length == 0) return null;
//        T min = a[0];
//        T max = a[0];
//        for (int i = 1; i < a.length; i++)
//        {
//            if(min.compareTo(a[i]) > 0) min = a[i];
//            if(max.compareTo(a[i]) < 0) max = a[i];
//        }
//        return new Pair<>(min, max);
//    }
    public static <T extends Comparable> T[] minmax(IntFunction<T[]> constr, T... a)
    {
        T[] result = constr.apply(2);
        if (a == null || a.length == 0) return null;
        result[0] = a[0];
        result[1] = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if(result[0].compareTo(a[i]) > 0) result[0] = a[i];
            if(result[1].compareTo(a[i]) < 0) result[1] = a[i];
        }
        return result;
    }
}