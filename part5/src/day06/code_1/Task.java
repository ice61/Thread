package day06.code_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

    //必备参数
    private static final long serialVersionUID = 1L;

    //产品集合
    private List<Product> products;

    //起始位置
    private int first;

    //终止位置
    private int last;

    //价格增百分比
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        //如果任务数量小于10
        if (last - first < 10) {
            //执行涨价操作
            updatePrices();
        } else {
            //如果任务数量大于10则将任务均分
            int middle = (first + last) / 2;
            //打印分割任务提示语
            System.out.printf("Task: Pending tasks:%s\n",
                    getQueuedTaskCount());
            //根据新分配的范围创建两个任务
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            //执行
            invokeAll(t1, t2);
        }
    }

    private void updatePrices() {
        //遍历集合为每一个商品做涨价操作
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }
}
