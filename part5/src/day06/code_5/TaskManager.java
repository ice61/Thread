package day06.code_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class TaskManager {

    //任务集合
    private List<ForkJoinTask<Integer>> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    //向集合中添加任务
    public void addTask(ForkJoinTask<Integer> task) {
        tasks.add(task);
    }

    public void cancelTasks(ForkJoinTask<Integer> cancelTask) {
        //取消除传入的任务以外的其他所有任务
        for (ForkJoinTask<Integer> task : tasks) {
            if (task != cancelTask) {
                //取消任务
                task.cancel(true);
                //打印取消信息
                ((SearchNumberTask) task).writeCancelMessage();
            }
        }
    }
}
