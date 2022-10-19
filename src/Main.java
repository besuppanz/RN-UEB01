import java.util.Stack;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Stack<Integer> sharedStack = new Stack<>();

        Producer producer = new Producer(sharedStack);
        Consumer consumer = new Consumer(sharedStack);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

    }
}
