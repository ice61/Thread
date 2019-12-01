package thread.part1.code;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SearchTask implements Runnable {
    @Override
    public void run() {
        //获取当前线程的名字
        String name = Thread.currentThread().getName();
        try {
            //执行doTask方法
            doTask(name);
            //在此捕获异常
        } catch (InterruptedException e) {
            System.out.printf("%s Interrupted\n",name);
            return;
        }
        //打印结果，表示线程已结束运行
        System.out.printf("%s End\n",name);
    }

    private void doTask(String name) throws InterruptedException {
        //将当前时间的毫秒值作为种子来创建一个随机数发生器
        Random random = new Random(new Date().getTime());
        //通过发生器得到一个浮点数*100后强转为整型
        int value = (int) (random.nextDouble()*100);
        //打印，表示线程已经开启
        System.out.printf("%s has started\n",name);
        //休眠
        TimeUnit.SECONDS.sleep(value);
    }

    public static void main(String[] args) {
        //创建一个线程组
        ThreadGroup searcher = new ThreadGroup("Searcher");
        //创建一个searchTask类
        SearchTask searchTask = new SearchTask();
        for (int i = 0; i < 10; i++) {
            //通过循环，以上面创建的searchTask类作为参数创建十个线程对象并开启
            Thread thread = new Thread(searcher, searchTask);
            thread.start();
            try {
                //每创建一个休眠一秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印线程组中存在的线程数量
        System.out.printf("Number of Threads: %d\n",searcher.activeCount());
        //通过list方法打印线程组内线程的信息
        System.out.printf("Information about the Thread Group\n");
        searcher.list();
        Thread[] threads = new Thread[searcher.activeCount()];
        //通过enumerate方法将线程组中的对象拷贝到名为threads的数组中
        searcher.enumerate(threads);
        for (int i = 0; i < threads.length; i++) {
            //遍历数组输出当前线程的名字和状态
            System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
        }
        //调用waitFinish方法等待某个线程的苏醒
        waitFinish(searcher);
        //调用线程组的interrupt方法向线程组内的线程发起中断
        searcher.interrupt();
    }

    private static void waitFinish(ThreadGroup searcher) {
        /*
        * 线程组内有十个线程
        * 如果当前线程数量大于9，则表示所有线程均未运行结束
        * 执行死循环，没检查一次状态休眠一秒
        * */
        while (searcher.activeCount() > 9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
