package day03.code_7;

import java.util.Random;

public class Consumer implements Runnable {

    //文件缓冲器
    private Buffer buffer;

    //构造方法传入缓冲器引用
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        //当缓冲器存在或将出现数据，就不断取出数据并处理
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            //模拟处理数据
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            //休眠随机的时间来模拟处理数据花费的不同时间
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
