package day04.code_7;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {

    //存储数据的容器
    private List<String> buffer;

    //交换器
    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
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
            System.out.printf("Producer: Cycle %d\n", cycle);
            //将数组装入容器中
            for (int j = 0; j < 10; j++) {
                String message = "Event " + (i * 10 + j);
                System.out.printf("Producer: %s\n", message);
                buffer.add(message);
            }
            try {
                //交换并得接收返回的数据
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印交换容器后容器中的数据量
            System.out.printf("Producer: %d\n", buffer.size());
            cycle++;
        }

    }
}
