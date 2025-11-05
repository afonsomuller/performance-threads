public class OneToOneModel {

    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        int[] threadCounts = {10, 100, 500, 1000};

        System.out.println("=== MODELO 1:1 ===");
        System.out.println("Cada thread de usuario mapeia para uma thread do SO\n");

        for (int count : threadCounts) {
            executeTest(count);
        }
    }

    private static void executeTest(int numThreads) {
        Thread[] threads = new Thread[numThreads];

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                performTask(threadId);
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;

        System.out.println("Threads: " + numThreads + " | Tempo: " + duration + " ms");
    }

    public static long executeTestAndReturn(int numThreads) {
        Thread[] threads = new Thread[numThreads];

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                performTask(threadId);
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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