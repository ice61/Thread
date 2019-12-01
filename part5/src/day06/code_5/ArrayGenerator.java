package day06.code_5;

import java.util.Random;

public class ArrayGenerator {

    public int[] generateArray(int size) {
        //根据传入的参数生成一个数组
        int[] array = new int[size];
        //创建随机数生成器对象
        Random random = new Random();
        //对数组进行初始化
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }
        //返回数组
        return array;
    }

}
