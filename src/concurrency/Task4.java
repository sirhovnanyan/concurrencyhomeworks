package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Task4 {
    public static void main(String[] args) throws InterruptedException {
      List<String> list=new ArrayList<>();
      for(int i=0;i<5;i++) {
          list.add(UUID.randomUUID().toString());
      }
      new WriterThread(list).start();
      new WriterThread(list).start();
      new RemoverThread(list).start();
    }
}

class WriterThread extends Thread{
    private final List<String> list;

    public WriterThread(List<String> list){
        if(list==null || list.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.list=list;
    }

    @Override
    public void run() {
        list.add(UUID.randomUUID().toString());
        System.out.println("Thread "+getId()+" added value to list");
    }
}

class RemoverThread extends Thread{
    private Random random=new Random();
    private final List<String> list;
    public RemoverThread(List<String> list){
        if(list==null || list.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.list=list;
    }

    @Override
    public void run() {
        list.remove(random.nextInt(list.size()));
        System.out.println("Thread "+getId()+" removed value from the list");
    }
}

