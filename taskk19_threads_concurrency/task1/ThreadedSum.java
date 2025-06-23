

// Importing core Java classes
import java.util.ArrayList;
import java.util.List;

/**
 * Each SumTask computes the sum of a specific range of numbers.
 */
class SumTask extends Thread {
    private int start, end;
    private long partialSum = 0;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // This method runs when the thread is started
    public void run() {
        for (int i = start; i <= end; i++) {

            System.out.println(partialSum += i);
        //    partialSum += i;
        }
    }

    public long getPartialSum() {
        return partialSum;
    }
}

/**
 * Main class that launches all threads and aggregates their results.
 */
public class ThreadedSum {

    public static void main(String[] args) throws InterruptedException {
        int totalNumbers = 1_000_000;
        int chunkSize = 10_000;
        int threadCount = totalNumbers / chunkSize;

        List<SumTask> threads = new ArrayList<>();

        // Create and start each thread
        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize + 1;  // To start at 1   0 * 10,000 + 1 = 1
            int end = (i + 1) * chunkSize; // To end at 10,000 (0+1) * 10,000 = 10,000
            SumTask task = new SumTask(start, end);
            threads.add(task); // append the computed to the List
            task.start(); // launches new thread
        }

        long grandTotal = 0;

        // Wait for all threads to finish and collect their results
        for (SumTask task : threads) {
            task.join(); // blocks until thread completes
            grandTotal += task.getPartialSum();
        }

        System.out.println("Grand Total Sum: " + grandTotal); // Expect 500000500000
    }
}
