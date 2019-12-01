package day04.code_7;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {

    //存储数据的容器
    private List<String> buffer;

    //交换器
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        //记录循环次数
        int cycle = 1;
        //总共需要交换十次数据
        for (int i = 0; i < 10; i++) {
            //打印这是第几次循环
            System.out.printf("Consumer: Cycle %d\n", cycle);
            try {
                //交换并得接收返回的数据
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //消费掉所有数据
            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.printf("Consumer: %s\n", message);
                buffer.remove(0);
            }
            cycle++;
        }

    }
}
