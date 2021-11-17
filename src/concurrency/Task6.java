package concurrency;

public class Task6 {
    public static void main(String[] args) throws InterruptedException {
        SafeSimpleList<Integer> safeList = new SafeSimpleList<>();
        safeList.add(1);
        safeList.add(2);
        safeList.add(3);
        safeList.add(4);
        safeList.add(5);
        safeList.printList();


        Runnable runnable1 = () ->
        {
            for (int i = 0; i < safeList.getSize(); i++) {
                System.out.println(safeList.get(i));
            }
        };

        Runnable runnable2 = () ->
        {
            for (int i = 0; i < safeList.getSize(); i++) {
                safeList.remove(4);
                safeList.add(i);
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        safeList.printList();
    }
}
