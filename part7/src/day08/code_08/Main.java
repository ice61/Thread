package day08.code_08;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建定制锁对象
        MyLock lock = new MyLock();
        //创建十个任务并分别开启线程执行
        for (int i = 0; i < 10; i++) {
            Task task = new Task(lock, "Task-" + i);
            Thread thread = new Thread(task);
            thread.start();
        }
        //主线程休眠两秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean value;
        //不断自旋尝试获取锁
        do {
            try {
                value = lock.tryLock(1, TimeUnit.SECONDS);
                //获取锁失败打印相关信息
                if (!value) {
                    System.out.printf("Main: Trying to get the Lock\n");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                value = false;
            }
        } while (!value);
        //打印成功获取锁信息
        System.out.println("Main: Got the lock");
        //释放锁
        lock.unlock();
        //打印程序结束信息
        System.out.println("Main: End of the program");
    }

}
