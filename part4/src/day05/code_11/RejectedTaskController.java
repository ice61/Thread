package day05.code_11;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedTaskController implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //打印被拒绝任务的信息
        System.out.printf("RejectedTaskController: " +
                "The task %s has been rejected\n", r);
        //打印执行器信息
        System.out.printf("RejectedTaskController: %s\n",
                executor.toString());
        //打印执行器是否正在关闭
        System.out.printf("RejectedTaskController: Terminating: %s\n",
                executor.isTerminating());
        //打印执行器是否已经关闭
        System.out.printf("RejectedTaskController: Terminated: %s\n",
                executor.isTerminated());
    }
}
