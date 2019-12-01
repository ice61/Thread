package day06.code_1;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        //创建产品生成对象
        ProductListGenerator generator = new ProductListGenerator();
        //通过产品生成器得到大小为10000的产品集合
        List<Product> products = generator.generate(10000);
        //创建一个任务
        Task task = new Task(products, 0, 10000, 0.20);
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        //调用线程池的方法执行任务
        pool.execute(task);
        do {
            //打印线程池中当前正在执行任务的线程数量
            System.out.printf("Main: Thread Count: %d\n",
                    pool.getActiveThreadCount());
            //打印线程池中窃取的工作数量
            System.out.printf("Main: Thread Steal: %d\n",
                    pool.getStealCount());
            //打印线程池的并行级别
            System.out.printf("Main: Parallelism: %d\n",
                    pool.getParallelism());
            //休眠5秒
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //等待任务结束
        } while (!task.isDone());
        //关闭线程池
        pool.shutdown();
        //判断任务是否抛出了异常
        if (task.isCompletedNormally()) {
            //打印任务无异常完成的提示信息
            System.out.printf("Main: The process has completed normally\n");
        }
        //检查商品是否已正确涨价
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getPrice() != 12) {
                System.out.printf("Product %s: %f\n",
                        product.getName(), product.getPrice());
            }
        }
        //打印程序结束提示语
        System.out.println("Main: End of the program\n");
    }

}
