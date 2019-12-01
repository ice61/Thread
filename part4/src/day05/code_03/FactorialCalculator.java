package day05.code_03;

import java.util.concurrent.Callable;

public class FactorialCalculator implements Callable<Integer> {

    //要进行阶乘的数字
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    //重写的可以返回结果的call方法
    @Override
    public Integer call() throws Exception {
        //将结果预置为1
        int result = 1;
        //如果需要阶乘的数为0或1
        if (number == 0 || number == 1) {
            //直接返回结果1
            result = 1;
        } else {
            //否则进行阶乘，每次阶乘后休眠20毫秒
            for (int i = 2; i <= number; i++) {
                result *= i;
                Thread.sleep(20);
            }
        }
        //打印线程名称和阶乘结果
        System.out.printf("%s: %d\n",
                Thread.currentThread().getName(), result);
        //返回结果
        return result;
    }
}
