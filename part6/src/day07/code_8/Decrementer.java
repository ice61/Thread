package day07.code_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Decrementer implements Runnable {

    //原子数组
    private AtomicIntegerArray vector;

    public Decrementer(AtomicIntegerArray vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i = 0; i < vector.length(); i++) {
            //将数组中指定位置的元素减少1
            vector.getAndDecrement(i);
        }
    }
}
