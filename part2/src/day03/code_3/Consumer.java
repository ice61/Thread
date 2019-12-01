package day03.code_3;

public class Consumer implements Runnable {

    //存储空间对象
    private EventStorage eventStorage;

    //有参构造方法
    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        /*
         * 循环向存储空间中装填数据
         * 每添加一个数据后休眠一段时间
         * */
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eventStorage.get();
        }
    }
}
