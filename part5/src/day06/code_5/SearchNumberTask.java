package day06.code_5;


import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class SearchNumberTask extends RecursiveTask<Integer> {

    //待搜索的数组
    private int[] numbers;

    //搜索范围
    private int start, end;

    //目标数字
    private int number;

    //任务管理器
    private TaskManager manager;

    //未查询到目标数字时返回的常量
    private static final int NOT_FOUND = -1;

    //必要参数
    private static final long serialVersionUID = 1L;

    public SearchNumberTask(int[] numbers, int start, int end,
                            int number, TaskManager manager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    @Override
    protected Integer compute() {
        //打印任务开始提示信息
        System.out.printf("Task: %d : %d\n", start, end);
        int ret;
        //如果搜索范围大于10
        if (end - start > 10) {
            //调用切割任务的方法
            ret = launchTasks();
        } else {
            //查找目标数字
            ret = lookForNumber();
        }
        //返回结果
        return ret;
    }

    private int launchTasks() {
        //切割任务
        int mid = (start + end) / 2;
        //创建两个新的任务在将其加入任务集合后执行
        SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid, number, manager);
        SearchNumberTask task2 = new SearchNumberTask(numbers, mid, end, number, manager);
        manager.addTask(task1);
        manager.addTask(task2);
        task1.fork();
        task2.fork();
        //返回值
        int returnValue;
        //获取任务1的结果
        returnValue = task1.join();
        //如果查询到了就返回索引
        if (returnValue != -1) {
            return returnValue;
        }
        //否则返回任务2的结果
        return task2.join();

    }

    private int lookForNumber() {
        //遍历搜索范围内的数组
        for (int i = start; i < end; i++) {
            //如果是目标数字
            if (numbers[i] == number) {
                //打印查找成功提示语
                System.out.printf("Task: Number %d found in position %d\n",
                        number, i);
                //调用任务管理器的方法取消其他任务
                manager.cancelTasks(this);
                //返回目标数字的索引
                return i;
            }
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //没有查询到，返回常量
        return NOT_FOUND;
    }

    public void writeCancelMessage() {
        //打印任务取消的提示信息
        System.out.printf("Task: Cancelled task from %d to %d\n",
                start, end);
    }
}
