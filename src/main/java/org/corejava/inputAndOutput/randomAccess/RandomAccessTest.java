package org.corejava.inputAndOutput.randomAccess;

import org.corejava.equals.Employee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.System.out;

public class RandomAccessTest
{
    public static void main(String[] args) throws IOException
    {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try (var out = new DataOutputStream(new FileOutputStream("employee.dat")))
        {
            for (Employee e : staff)
                writeData(out, e);
        }
        try (var in = new RandomAccessFile("employee.dat", "r"))
        {
            int n = (int) (in.length() / Employee.RECORD_SIZE);
            var newStaff = new Employee[n];
            for (int i = n - 1; i >= 0; i--)
            {
                in.seek((long) i * Employee.RECORD_SIZE);
                newStaff[i] = readData(in);
//                out.println("newStaff[" + i + "]" + newStaff[i]);
            }

            for (Employee e : newStaff)
                out.println(e);
        }
    }
    private static void writeData(DataOutput out, Employee e) throws IOException
    {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());

        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    private static Employee readData(DataInput in) throws IOException
    {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m - 1, d);
    }


}
