package org.corejava.equals;

public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day){
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public double getSalary(){
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }
    
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    public double getBonus()
    {
        return bonus;
    }
    @Override
    public boolean equals(Object otherObject) {
        if(!super.equals(otherObject)) return false;
        Manager other = (Manager) otherObject;

        return bonus == other.bonus;
    }

    public int hashCode(){
        return java.util.Objects.hash(super.hashCode(), bonus);
    }

    public String toString(){
        return super.toString() + "[bonus=" + bonus + "]";
    }
    public void test(){
        System.out.println("ancestor.pro_field:" + super.pro_field);
    }
}
