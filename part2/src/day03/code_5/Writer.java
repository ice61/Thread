package day03.code_5;

public class Writer implements Runnable {

    //价格信息对象
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        /*
         * 循环三次
         * 使用随机数来设定价格1、2
         * 每次更改价格后休眠2秒
         * */
        for (int i = 0; i < 3; i++) {
            pricesInfo.setPrice(Math.random() * 10, Math.random() * 8);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
