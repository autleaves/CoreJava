package org.corejava.inputAndOutput.serialClone;

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;

import static java.lang.System.out;

public class SerialCloneTest
{
    public static void main(String[] args) throws CloneNotSupportedException
    {
        var harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        var harry2 = (Employee) harry.clone();

        harry.raiseSalary(10);

        out.println(harry);
        out.println(harry);
    }
}
class SerialCloneable implements Cloneable, Serializable
{
    public Object clone() throws CloneNotSupportedException
    {
        try {
            var bout = new ByteArrayOutputStream();
            try (var out = new ObjectOutputStream(bout))
            {
                out.writeObject(this);
            }
            try (var bin = new ByteArrayInputStream(bout.toByteArray()))
            {
                var in = new ObjectInputStream(bin);
                return in.readObject();
            }
        } catch (IOException | ClassNotFoundException e)
        {
            var e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}

class Employee extends SerialCloneable
{
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }
    public LocalDate getHireDay(){
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString(){
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }

}

