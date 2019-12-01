package day03.code_5;

public class Reader implements Runnable {

    //价格信息对象
    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        //循环十次获取价格1、2
        for (int i = 0; i < 10; i++) {
            pricesInfo.getPrice1();
            pricesInfo.getPrice2();
        }
    }
}
