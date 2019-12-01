package thread.part1.code;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourcesLoader implements Runnable {
    @Override
    public void run() {
        //打印线程开始执行的时间
        System.out.printf("Beginning data sources loading: %s \n",new Date());
        try {
            //线程休眠4秒
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印线程结束运行的时间
        System.out.printf("Data sources loading has finished: %s\n",new Date());
    }

    public static void main(String[] args) {
        //创建对象并作为传入参数以此来创建一个线程
        DataSourcesLoader dsl = new DataSourcesLoader();
        Thread thread1 = new Thread(dsl, "DataSourcesLoader");
        //创建对象并作为传入参数以此来创建一个线程
        NetworkConnectionsLoader ncl = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(ncl, "NetworkConnectionsLoader");
        //开启线程
        thread1.start();
        thread2.start();

        try {
            //主线程挂起，只有当thread1和thread2都执行完毕之后主线程才会继续向下执行
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印主线程结束的时间
        System.out.printf("Main: Configuration has been loaded: %s\n",new Date());
    }
}
