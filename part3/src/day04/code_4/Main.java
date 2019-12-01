package day04.code_4;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        //行数为10000行
        final int ROWS = 10000;
        //列数为1000列
        final int LENGTH = 1000;
        //待查询数据为5
        final int SEARCH = 5;
        //五个查询线程
        final int PARTICIPANTS = 5;
        //每个线程查询2000行
        final int LINES_PARTICIPANT = 2000;
        //创建大矩阵对象
        MatrixMock mock = new MatrixMock(ROWS, LENGTH, SEARCH);
        //创建结果对象
        Results results = new Results(ROWS);
        //创建汇总结果对象
        Grouper grouper = new Grouper(results);
        //创建CyclicBarrier对象并将结果对象作为第二个参数传入
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);
        //创建五个线程开始搜索
        for (int i = 0; i < 5; i++) {
            Searcher searcher = new Searcher(i * LINES_PARTICIPANT,
                    (i + 1) * LINES_PARTICIPANT, mock, results, SEARCH, barrier);
            Thread thread = new Thread(searcher);
            thread.start();
        }
        //打印主线程结束提示语
        System.out.println("Main: The main thread has finished");

    }

}
