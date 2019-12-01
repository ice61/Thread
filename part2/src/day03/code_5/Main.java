package day03.code_5;

public class Main {

    public static void main(String[] args) {
        //创建价格信息类、读类、写类
        PricesInfo pricesInfo = new PricesInfo();
        Writer writer = new Writer(pricesInfo);
        Reader reader = new Reader(pricesInfo);
        //创建5个读线程并开启
        Thread[] threadReader = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threadReader[i] = new Thread(reader);
            threadReader[i].start();
        }
        //创建一个写线程并开启
        Thread writerThread = new Thread(writer);
        writerThread.start();
    }

}
