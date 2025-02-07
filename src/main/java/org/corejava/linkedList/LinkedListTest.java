package org.corejava.linkedList;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import static java.lang.System.out;

public class LinkedListTest {

    public static void main(String... args)
    {
        var a = new LinkedList<String>();
        a.add("Amy");
        a.add("Erica");
        a.add("Carl");

        // Collections.sort(a);

        var b = new LinkedList<String>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext())
        {
            if(aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        out.println(a);

        bIter = b.iterator();
        while(bIter.hasNext())
        {
            bIter.next();
            if (bIter.hasNext())
            {
                bIter.next();
                bIter.remove();
            }
        }

        out.println(b);

        a.removeAll(b);

        out.println(a);
    }
}
