package telran.numbers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadsPoolGroupSum extends ThreadsGroupSum {
    private final ExecutorService executor;

    public ThreadsPoolGroupSum(int[][] groups) {
        super(groups);
        executor = Executors.newFixedThreadPool(4);
    }
    
    protected void startTasks(FutureTask<Long>[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
            executor.execute(tasks[i]);
        }
        executor.shutdown();
    }

}
