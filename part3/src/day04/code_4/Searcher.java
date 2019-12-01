package day04.code_4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {

    //搜索起始行（包括）
    private int firstRow;
    //搜索截止行（不包括）
    private int lastRow;
    //大矩阵
    private MatrixMock mock;
    //结果类对象
    private Results results;
    //待查询数据
    private int number;
    //用于同步的CyclicBarrier对象
    private CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow, MatrixMock mock,
                    Results results, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.results = results;
        this.number = number;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        //声明一个计数器
        int counter;
        //打印搜索的起始、截止行
        System.out.printf("%s: Processing lines from %d to %d\n",
                Thread.currentThread().getName(), firstRow, lastRow);
        for (int i = firstRow; i < lastRow; i++) {
            //循环得到子集中每一行的数据
            int[] row = mock.getRow(i);
            //每一行都重新统计待查询数据的个数
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == number)
                    counter++;
            }
            //查询一行后，写入结果
            results.setData(i, counter);
        }
        //打印当前行查询结束信息
        System.out.printf("%s: Lines processed\n",
                Thread.currentThread().getName());
        try {
            //等待其他行查询结束
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
