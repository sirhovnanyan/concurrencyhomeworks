package concurrency;

import java.util.Random;

public class Task5 {
    public static void main(String[] args) {
        Random random = new Random();
        CartesianSystem cartesianSystem = new CartesianSystem();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                cartesianSystem.setXY(random.nextInt() % 10, random.nextInt() % 10);
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
    private int x;
    private int y;

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Set: (" + this.x + "," + this.y + ")");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
