
class A extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Hi");
        }
    }
}

class B extends Thread {
    public void run() {
        int j;
        for (j = 0; j < 100; j++) {
            System.out.println("World");
        }
    }
}


public class Threadss {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        // a.start();
        // b.start();

        // GET NAME OF THE THREAD USING 
        // a) USING THREAD CLASS
        class MyThread extends Thread {
            public void run() {
                System.out.println("Running in thread: " + Thread.currentThread().getName());
            }

        }
        MyThread name = new MyThread();
        name.start();


        // b) USING RUNNABLE INTERFACE
        Runnable task = () -> System.out.println("Running in thread: "+ Thread.currentThread().getName());
        Thread thread = new Thread(task);
        thread.start();
    }

}