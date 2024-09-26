import java.util.concurrent.*;

public class Scenario1 {
    private static BlockingQueue<Task> filaDeTasks = new LinkedBlockingDeque<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(10);
    private static ConcurrentHashMap<Long, Long> resultados = new ConcurrentHashMap<>();

    public static void main(String[] args) {    
        for (long i = 0; i < 5; i++) {
            executor.submit(new TaskProducer(i, filaDeTasks));
        }

        for (long i = 0; i < 3; i++) {
            executor.submit(new Node(filaDeTasks, resultados));
        }

        executor.submit(() -> {
            try {
                while(!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(5000);
                    exibirResultados();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

        });
    }
    private static void exibirResultados() {
        resultados.forEach((taskId, result) -> {
            System.out.println("Tarefa ID: " + taskId + " -> Resultado: " + result);
        });
    }
}
