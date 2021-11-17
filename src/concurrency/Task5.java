package concurrency;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task5 {
    public static void main(String[] args) {
        Random random = new Random();
        CartesianSystem cartesianSystem = new CartesianSystem();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                    cartesianSystem.setXY(random.nextInt()%2, random.nextInt()%2);
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                    System.out.println("Get: (" + cartesianSystem.getX() +
                            "," + cartesianSystem.getY() + ")");
            }
        }).start();
    }
}

class CartesianSystem {
    ReadWriteLock lock=new ReentrantReadWriteLock();
    private int x;

    private int y;

    public void setXY(int x, int y) {
        lock.writeLock().lock();
        try {
            if((x==1 && y==1) || (x==-1 && y==-1)) {
                this.x = x;
                this.y = y;
                System.out.println("Set: (" + this.x + "," + this.y + ")");
            }
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    public int getX() {
        lock.readLock().lock();
        try {
            return x;
        }
        finally {
            lock.readLock().unlock();
        }
    }

    public int getY() {
        lock.readLock().lock();
        try {
            return y;
        }
        finally {
            lock.readLock().unlock();
        }
    }
}
