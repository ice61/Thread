package day07.code_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Incrementer implements Runnable {

    //原子数组
    private AtomicIntegerArray vector;

    public Incrementer(AtomicIntegerArray vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i = 0; i < vector.length(); i++) {
            //将数组中指定位置的元素增加1
            vector.getAndIncrement(i);
        }
    }
}
