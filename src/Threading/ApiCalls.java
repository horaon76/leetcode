package Threading;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class ApiCalls {

    public static void main(String[] args) {
//        LambdaExample();
//        CompletableFutureExampleWithExecutorService();
          CompletableFutureExampleWithoutExecutorService();
    }
    public static void CompletableFutureExampleWithoutExecutorService() {
        List<Integer> ids = IntStream.range(1, 101).boxed().toList(); // List of 100 IDs

        // Using CompletableFuture without an explicit ExecutorService
        List<CompletableFuture<Void>> futures = ids.stream()
                .map(id -> CompletableFuture.runAsync(() -> {
                    callApiWithId(id);
                        long threadId = Thread.currentThread().getId(); // Get thread ID
                        System.out.println("API call with ID: " + id + " executed by Thread ID: " + threadId);
                    }
                )) // Default uses ForkJoinPool
                .toList();

        // Combine all futures and wait for them to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
    public static void CompletableFutureExampleWithExecutorService() {
        List<Integer> ids = IntStream.range(1, 101).boxed().toList(); // List of 100 IDs

        int threadPoolSize = 10; // Thread pool size
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize); // Thread pool

        // Submit tasks to thread pool using CompletableFuture
        List<CompletableFuture<Void>> futures = ids.stream()
                .map(id -> CompletableFuture.runAsync(() -> callApiWithId(id), executorService)) // CompletableFuture
                .toList();

        // Combine all futures and wait for them to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        executorService.shutdown(); // Shut down the thread pool
    }

    public static void LambdaExample() {
        List<Integer> ids = IntStream.range(1, 101).boxed().toList(); // Sample list of 100 IDs

        int threadPoolSize = 10; // Set the size of the thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        try {
            // Submit tasks to the thread pool using lambda
            List<? extends Future<?>> futures = ids.stream()
                    .map(id -> executorService.submit(() -> {
                        callApiWithId(id); // Lambda function for each API call
                    }))
                    .toList();

            // Optional: Wait for all tasks to complete (if you need to ensure completion)
            for (Future<?> future : futures) {
                future.get(); // This will block until the task is complete
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown(); // Ensure the ExecutorService shuts down
        }
    }

    // Simulated API call with ID
    private static void callApiWithId(int id) {
        try {
            // Simulate API call delay
            Thread.sleep(100); // Simulates API latency
            System.out.println("Called API with ID: " + id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}
