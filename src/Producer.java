import java.util.Random;
import java.util.Stack;

public class Producer extends Thread {
    private Stack<Integer> stack;
    private Random rand = new Random();
    private int value = 0;

    public Producer(Stack<Integer> stack) {
        this.stack = stack;
    }

    public void run() {
        while(true) {
            value = rand.nextInt(50) + 1;
            stack.push(value);
        }
    }
}
