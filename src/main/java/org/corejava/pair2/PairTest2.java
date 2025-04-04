package org.corejava.pair2;

import org.corejava.equals.Employee;
import org.corejava.equals.Manager;

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

//        var ceo = new Manager("ceo", 11000, 1906, 12, 9);
//        var cfo = new Manager("cfo", 12000, 1903, 12, 9);
//        var managerBuddies = new Pair<Manager>(ceo, cfo);
//        Pair<? super Manager> wildcardBuddies = managerBuddies;
//        out.println(managerBuddies.getFirst());
//        out.println(wildcardBuddies.getFirst().getClass().getName());
//        wildcardBuddies.setFirst(new Manager("cto", 10000, 1916, 12, 9));
//        out.println(managerBuddies.getFirst());


        Manager[] managers =
                {
                        new Manager("AA", 10000,1906, 12, 9),
                        new Manager("BB", 11000,1907, 12, 10),
                        new Manager("CC", 12000,1908, 12, 11),
                        new Manager("DD", 13000,1909, 12, 12),
                };
        double bonus = 500;
        for (Manager m : managers)
        {
            m.setBonus(bonus *= 2);
        }
        Pair<Manager> pair = new Pair<Manager>();
        ArrayAlg.minmaxBonus(managers, pair);
        out.println("Min = " + pair.getFirst().getBonus() + ", Max = " + pair.getSecond().getBonus());
        
    }
}
class ArrayAlg {

    public static <T extends Comparable<T>> T[] minmax(IntFunction<T[]> constr, T... a)
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
//    public static <T extends Comparable<? extends T>> Pair<T> minmax(T... a)
//    {
//        T[] result = constr.apply(2);
//        if (a == null || a.length == 0) return null;
//        result[0] = a[0];
//        result[1] = a[0];
//        for (int i = 1; i < a.length; i++)
//        {
//            if(result[0].compareTo(a[i]) > 0) result[0] = a[i];
//            if(result[1].compareTo(a[i]) < 0) result[1] = a[i];
//        }
//        return result;
//    }
    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result)
    {
        if (a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.getBonus() > a[i].getBonus()) min = a[i];
            if (min.getBonus() < a[i].getBonus()) max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }
    public static <T> void swapHelper(Pair<T> p)
    {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}