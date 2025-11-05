public class Main {

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("  COMPARACAO DE DESEMPENHO: MODELOS DE THREADS");
        System.out.println("===============================================\n");

        int[] threadCounts = {10, 100, 500, 1000};

        System.out.println("Executando Modelo 1:1...\n");
        long[] tempos1to1 = executarModelo1to1(threadCounts);

        System.out.println("\n-------------------------------------------\n");

        System.out.println("Executando Modelo N:M...\n");
        long[] temposNtoM = executarModeloNtoM(threadCounts);

        System.out.println("\n===============================================");
        System.out.println("  RESULTADOS COMPARATIVOS");
        System.out.println("===============================================\n");

        exibirComparacao(threadCounts, tempos1to1, temposNtoM);
    }

    private static long[] executarModelo1to1(int[] threadCounts) {
        long[] tempos = new long[threadCounts.length];

        System.out.println("=== MODELO 1:1 ===");
        System.out.println("Cada thread de usuario mapeia para uma thread do SO\n");

        for (int i = 0; i < threadCounts.length; i++) {
            tempos[i] = OneToOneModel.executeTestAndReturn(threadCounts[i]);
            System.out.println("Threads: " + threadCounts[i] + " | Tempo: " + tempos[i] + " ms");
        }

        return tempos;
    }

    private static long[] executarModeloNtoM(int[] threadCounts) {
        long[] tempos = new long[threadCounts.length];

        System.out.println("=== MODELO N:M ===");
        System.out.println("N threads de usuario mapeadas para M threads do SO (M=8)\n");

        for (int i = 0; i < threadCounts.length; i++) {
            tempos[i] = NToMModel.executeTestAndReturn(threadCounts[i]);
            System.out.println("Threads: " + threadCounts[i] + " | Tempo: " + tempos[i] + " ms");
        }

        return tempos;
    }

    private static void exibirComparacao(int[] threadCounts, long[] tempos1to1, long[] temposNtoM) {
        System.out.printf("%-10s | %-12s | %-12s | %-12s | %-10s\n",
                "Threads", "Modelo 1:1", "Modelo N:M", "Diferenca", "Mais Rapido");
        System.out.println("---------------------------------------------------------------------");

        for (int i = 0; i < threadCounts.length; i++) {
            long diferenca = Math.abs(tempos1to1[i] - temposNtoM[i]);
            String maisRapido = tempos1to1[i] < temposNtoM[i] ? "1:1" : "N:M";

            System.out.printf("%-10d | %-12d | %-12d | %-12d | %-10s\n",
                    threadCounts[i],
                    tempos1to1[i],
                    temposNtoM[i],
                    diferenca,
                    maisRapido);
        }

        System.out.println("\n===============================================");
    }
}