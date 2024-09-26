import java.util.concurrent.BlockingQueue;

public class Node implements Runnable {

  private final BlockingQueue<Task> filaDeTasks;

  public Node(BlockingQueue<Task> filaDeTasks) {
    this.filaDeTasks = filaDeTasks;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        Task task = filaDeTasks.take();
        task.execute();
        task.getExecDuration();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
}
