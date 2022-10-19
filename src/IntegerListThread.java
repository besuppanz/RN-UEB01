import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegerListThread extends Thread {
    private static IntegerList integerList = new IntegerList();
    private int values;
    private Random rand = new Random();

    public IntegerListThread(int values) {
        this.values = values;
    }


    public void run() {
        for (int i = 0; i < values; i++) {
            integerList.add(rand.nextInt(50) + 1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 10;
        int values = 20;

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            threads.add(new IntegerListThread(values));
            threads.get(i).start();
        }

        for (int i = 0; i < numThreads; i++) {
            threads.get(i).join();
        }

        for (int i = 0; i < integerList.toArray().length; i++) {
            System.out.println(integerList.get(i));
        }
    }
}