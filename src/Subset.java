import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    private RandomizedQueue<Object> randomizedQueue = new RandomizedQueue<Object>();

    public static void main(String[] args) {
        new Subset().run(Integer.parseInt(args[0]));
    }

    private void run(int iterations) {

        String input;

        while (!StdIn.isEmpty()) {
            input = StdIn.readString();
            randomizedQueue.enqueue(input);
        }

        for (int i = 0; i < iterations; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
