package org.corejava.inputAndOutput.objectStream;

import org.corejava.equals.Employee;
import org.corejava.equals.Manager;

import java.io.*;

import static java.lang.System.out;

public class ObjectStreamTest
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        var hary = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        var carl = new Manager("Carl Cracker", 75000, 1987, 12, 15);
        var tony = new Manager("Tony Tester", 40000, 1990, 3, 15);

        carl.setSecretary(hary);
        tony.setSecretary(hary);

        Employee[] staff = new Employee[3];

        staff[0] = carl;
        staff[1] = hary;
        staff[2] = tony;

        try (var out = new ObjectOutputStream(new FileOutputStream("employee.dat")))
        {
            out.writeObject(staff);
        }
        try (var in = new ObjectInputStream(new FileInputStream("employee.dat")))
        {
            var newStaff = (Employee[]) in.readObject();
            newStaff[1].raiseSalary(10);
            for(Employee e : newStaff)
                out.println(e);
        }

    }

}
