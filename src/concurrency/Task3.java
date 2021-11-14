package concurrency;

//Deadlock

public class Task3 {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}

class Thread1 extends Thread {
    public void run() {
        System.out.println("Thread1: Try locked 'lock1' object monitor");
        synchronized (Task3.lock1) {
            System.out.println("Thread1: 'lock1' object monitor locked");

            System.out.println("Thread1: Try locked 'lock2' object monitor");
            synchronized (Task3.lock2) {
                System.out.println("Thread1: Object 'lock1' and 'lock2' monitors are locked");
            }
        }
    }
}

class Thread2 extends Thread {
    public void run() {
        System.out.println("Thread2: Try locked 'lock2' object monitor");
        synchronized (Task3.lock2) {
            System.out.println("Thread2: 'lock2' object monitor locked");

            System.out.println("Thread2: Try locked 'lock1' object monitor");
            synchronized (Task3.lock1) {
                System.out.println("Thread2: Object 'lock1' and 'lock2' monitors are locked");
            }
        }
    }
}

