package concurrency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<File> files = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            File file = new File("C:\\Homework\\file" + i + ".txt");
            files.add(file);
        }

        for (File file : files) {
            FileWriter writer = new FileWriter(file);
            for (int j = 0; j < 10000000; j++) {
                writer.write(UUID.randomUUID() + "\n");
            }
        }
        System.out.println("Single thread...");
        System.out.println(System.currentTimeMillis() - start); //output 128421 millisecond

        List<Thread> threads = new ArrayList<>();

        long start1 = System.currentTimeMillis();

        for (File file : files) {
            threads.add(
                    new WriteThread(
                            () -> {
                                FileWriter writer = null;
                                try {
                                    writer = new FileWriter(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                for (int j = 0; j < 10000000; j++) {
                                    try {
                                        writer.write(UUID.randomUUID() + "\n");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                    )
            );
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Threads...");
        System.out.println((System.currentTimeMillis() - start1)); //output 190699 milliseconds
    }
}

class WriteThread extends Thread {
    public WriteThread(Runnable target) {
        super(target);
    }
}
