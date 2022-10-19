import java.util.Random;
import java.util.Stack;

public class Consumer extends Thread {

    private Stack<Integer> stack;
    private Random rand = new Random();
    private int consumeCount = 0;
    private int result = 0;

    public Consumer(Stack<Integer> stack) {
        this.stack = stack;
    }

    public void run() {
        while(true) {
            System.out.println("Consumer: Stacksize: " + stack.size());

            consumeCount = rand.nextInt(stack.size()) + 1;

            System.out.println("Consumer is consuming " + consumeCount+ " values");

            for(int i = 0; i < consumeCount; i++) {
                result += stack.pop();
            }

            System.out.println("Result of consumed values: " + result);
            result = 0;

            try {
                this.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
