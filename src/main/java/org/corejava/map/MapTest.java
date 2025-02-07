package org.corejava.map;

import java.util.HashMap;
import org.corejava.equals.Employee;
import static java.lang.System.out;

public class MapTest
{
	public static void main(String[] args)
	{
		var staff = new HashMap<String, Employee>();
		staff.put("144-25-5464", new Employee("Amy lee"));
		staff.put("567-24-2546", new Employee("Harry Hacker"));
		staff.put("157-62-7935", new Employee("Gary Cooper"));
		staff.put("456-62-5527", new Employee("Francesca Cruz"));

		out.println(staff);
		out.println("========================");
		staff.remove("567-24-2546");
		staff.put("456-62-5527", new Employee("Francesca Miller"));
		out.println(staff.get("456-62-5527"));
		out.println("========================");

		staff.forEach((k, v)->out.println("key=" + k + ", value=" + v));
	}
}
