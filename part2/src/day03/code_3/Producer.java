package day03.code_3;

public class Producer implements Runnable {

    //存储空间对象
    private EventStorage eventStorage;

    //有参构造方法
    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        /*
         * 循环从存储空间中取出数据
         * 每取出一个数据后休眠一段时间
         * 取出数据和存入数据后休眠的时间不同
         * 更符合实际情况，运行效果也更好
         * */
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eventStorage.set();
        }
    }
}
