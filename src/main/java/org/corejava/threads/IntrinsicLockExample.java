package org.corejava.threads;

class SharedResource {
    public synchronized void criticalSection() {
        System.out.println(Thread.currentThread().getName() + " has acquired the lock");
        try {
            Thread.sleep(3000); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is releasing the lock");
    }
}

public class IntrinsicLockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = () -> resource.criticalSection();

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}

