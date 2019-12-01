package thread.part1.code;

import java.util.Date;
import java.util.Random;

public class ErrorTask implements Runnable {
    @Override
    public void run() {
        int result;
        Random random = new Random(new Date().getTime());
        while (true) {
            //当除数为零时将抛出异常
            result = 100/(int)(random.nextDouble()*100);
            System.out.printf("%s: %d\n",
                    Thread.currentThread().getId(),result);
            //检查中断标志是否为true
            if (Thread.interrupted()) {
                //打印中断信息后中断
                System.out.printf("%d Interrupted\n",
                        Thread.currentThread().getId());
                return;
            }
        }
    }
}
