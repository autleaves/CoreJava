package org.corejava.threads;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

public class Bank
{
    private ReentrantLock bankLock = new ReentrantLock();
    private final double[] accounts;

    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }
    public void transfer(int from, int to, double amount)
    {

        bankLock.lock();
        try
        {
            if (accounts[from] < amount) return;
            out.println(Thread.currentThread());
            accounts[from] -= amount;
            out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        }
        finally {
            bankLock.unlock();
        }


    }

    private double getTotalBalance()
    {
        double sum = 0;
        for (double a : accounts)
            sum += a;
        return sum;
    }
    public int size()
    {
        return accounts.length;
    }
}
