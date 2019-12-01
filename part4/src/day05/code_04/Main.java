package day05.code_04;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        //创建两个用户验证对象
        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");
        //创建两个任务验证对象，并将用户验证对象作为参数传入
        TaskValidator ldapTask = new TaskValidator(ldapValidator);
        TaskValidator dbTask = new TaskValidator(dbValidator);
        //将两个任务验证对象放入集合中
        ArrayList<TaskValidator> taskLists = new ArrayList<>();
        taskLists.add(ldapTask);
        taskLists.add(dbTask);
        //创建一个缓存执行器
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //声明字符串
        String result;
        try {
            //取得一个结果
            result = executor.invokeAny(taskLists);
            System.out.printf("Main: Result: %s\n", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //关闭执行器并打印关闭信息
        executor.shutdown();
        System.out.printf("Main: End of the Execution\n");
    }

}
