package concurrency;

public class Task1 {
    public static void main(String[] args) {
        long sum = 0;
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 10_000_000; i++) {
            sum += i;
        }

        System.out.println("Single thread...");
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start);

        MyThread thread1 = new MyThread(1, 5000000);
        MyThread thread2 = new MyThread(5000001, 10000000);
        long start1 = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counts with 2 threads...");
        System.out.println(thread1.getSum() + thread2.getSum());
        System.out.println(System.currentTimeMillis() - start1);
    }
}

class MyThread extends Thread {
    private long sum;
    private final int start, end;

    public MyThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            sum += i;
        }
    }

    public long getSum() {
        return this.sum;
    }
}


