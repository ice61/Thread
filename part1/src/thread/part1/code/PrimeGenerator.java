package thread.part1.code;

public class PrimeGenerator extends Thread {

    @Override
    public void run() {
        //从1开始进行判断
        long number = 1L;
        //设置一个死循环
        while (true) {
            //通过isPrime方法判断number是否是一个质数
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }
            //通过isInterrupted方法检测中断，在这里我们检测到中断后就返回
            if (isInterrupted()) {
                System.out.println("The Prime Generator has been Interrupted");
                return;
            }
            number++;
        }
    }

    //判断一个数字是否是质数
    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();
        //运行线程后，主线程休眠5秒后发起中断
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //发起中断
        task.interrupt();
    }
}
