package Threading;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running!");
    }
}


class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable thread is running!");
    }
}

public class SimpleThread {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        //Runnable
        Thread runnableThread = new Thread(new MyRunnable());
        runnableThread.start();

        //Lambda
        Thread lambdaThread = new Thread(() -> {
            System.out.println("Lambda thread is running!");
        });
        lambdaThread.start();

        //Executor Service
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Executor thread is running!"));
        executor.shutdown();

        //Completable future
        CompletableFuture.runAsync(() -> {
            System.out.println("CompletableFuture thread is running!");
        });
    }
}
