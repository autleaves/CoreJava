package org.corejava.set.treeSet;

import java.util.*;
import static java.lang.System.out;
public class TreeSetTest
{
	public static void main(String... args)
	{
		var parts = new TreeSet<Item>();
		parts.add(new Item("Toaster", 11234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 9912));
		out.println(parts);

		var sortByDescription = new TreeSet<Item>(Comparator.comparing(Item::getDescription));

		sortByDescription.addAll(parts);
		out.println(sortByDescription);
	}
}