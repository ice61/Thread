package day06.code_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class LineTask extends RecursiveTask<Integer> {

    //必备参数
    private static final long serialVersionUID = 1L;

    //行数据
    private String line[];

    //起始、结束位置
    private int start, end;

    //待查找的词汇
    private String word;

    public LineTask(String[] line, int start, int end, String word) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        //初始化计数器
        int result = 0;
        //如果一行的数据小于100
        if (end - start < 100) {
            //查找指定词汇的数量
            result = count(line, start, end, word);
        } else {
            //分割任务
            int mid = (start + end) / 2;
            LineTask task1 = new LineTask(line, start, mid, word);
            LineTask task2 = new LineTask(line, mid, end, word);
            //执行
            invokeAll(task1, task2);
            //获取子任务的结果
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //将子任务结果相加后返回
    private Integer groupResults(Integer number1, Integer number2) {
        return number1 + number2;
    }

    private int count(String[] line, int start, int end, String word) {
        //初始化计数器
        int counter = 0;
        //查找每一个元素是否为指定的词汇
        for (int i = start; i < end; i++) {
            if (line[i].equals(word)) {
                counter++;
            }
        }
        //休眠10毫秒
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //返回结果
        return counter;
    }
}
