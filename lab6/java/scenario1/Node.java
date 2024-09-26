import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Node implements Runnable {

  private final BlockingQueue<Task> filaDeTasks;
  private final ConcurrentHashMap<Long, Long> resultados;

  public Node(BlockingQueue<Task> filaDeTasks, ConcurrentHashMap<Long, Long> resultados) {
    this.filaDeTasks = filaDeTasks;
    this.resultados = resultados;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        Task task = filaDeTasks.take();
        task.execute();
        resultados.put(task.id, task.getExecDuration());
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
}
