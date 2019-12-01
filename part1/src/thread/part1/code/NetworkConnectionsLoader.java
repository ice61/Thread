package thread.part1.code;

        import java.util.Date;
        import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable {
    @Override
    public void run() {
        //打印线程开始执行的时间
        System.out.printf("Beginning network connections loading: %s \n",new Date());
        try {
            //休眠6秒
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印线程结束运行的时间
        System.out.printf("Network connections loading has finished: %s\n",new Date());
    }
}
