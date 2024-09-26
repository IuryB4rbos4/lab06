import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class TaskProducer implements Runnable {
    private final long ProducerID;
    private final BlockingQueue<Task> filaDeTasks;
    private final AtomicLong tarefaID;

    public TaskProducer(long ProducerID, BlockingQueue<Task> filadeTasks){
        this.ProducerID = ProducerID;
        this.filaDeTasks = filadeTasks;
        this.tarefaID = new AtomicLong(0);
    }

    public long getProducerID() {
        return ProducerID;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Task task = new Task(tarefaID.incrementAndGet());
                Thread.sleep(5000);
                System.out.println("task " + tarefaID + "produzida pelo TaskProducer:" + ProducerID);
                filaDeTasks.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}