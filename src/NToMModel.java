import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NToMModel {

    private static final int ITERATIONS = 1000;
    private static final int POOL_SIZE = 8;

    public static void main(String[] args) {
        int[] threadCounts = {10, 100, 500, 1000};

        System.out.println("=== MODELO N:M ===");
        System.out.println("N threads de usuario mapeadas para M threads do SO (M=" + POOL_SIZE + ")\n");

        for (int count : threadCounts) {
            executeTest(count);
        }
    }

    private static void executeTest(int numThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            executor.submit(() -> {
                performTask(threadId);
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;

        System.out.println("Threads: " + numThreads + " | Tempo: " + duration + " ms");
    }

    public static long executeTestAndReturn(int numThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            executor.submit(() -> {
                performTask(threadId);
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static void performTask(int threadId) {
        long sum = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            sum += Math.sqrt(i) * Math.sin(i);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}