
// Import concurrency classes
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Represents a simple order with a unique ID.
 */
class Order {
    private static int counter = 0;
    private final int id;

    public Order() {
        this.id = ++counter;
    }

    public int getId() {
        return id;
    }
}

/**
 * A customer is a producer that puts an Order in the queue.
 */
class Customer implements Runnable {
    private final BlockingQueue<Order> queue;

    public Customer(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Order order = new Order();
            queue.put(order); // waits if queue is full
            System.out.println("Customer placed order: #" + order.getId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupted state
        }
    }
}

/**
 * The Chef is a consumer that takes Orders from the queue and processes them.
 */
class Chef implements Runnable {
    private final BlockingQueue<Order> queue;

    public Chef(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                Order order = queue.take(); // waits if queue is empty
                System.out.println("Chef is preparing order: #" + order.getId());
                Thread.sleep(500); // simulate time to prepare
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // clean up if interrupted
        }
    }
}

/**
 * Main driver class for the restaurant simulation.
 */
public class RestaurantSimulation {

    public static void main(String[] args) {
        // Shared thread-safe queue with max size 10
        BlockingQueue<Order> orderQueue = new ArrayBlockingQueue<>(10);

        // Start the chef thread
        Thread chef = new Thread(new Chef(orderQueue));
        chef.start();

        // Simulate multiple customers placing orders
        for (int i = 0; i < 20; i++) {
            Thread customer = new Thread(new Customer(orderQueue));
            customer.start();

            // Small delay to simulate staggered customer arrival
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
