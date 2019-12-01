package day06.code_2;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer> {

    //必备参数
    private static final long serialVersionUID = 1L;

    //文档
    private String[][] document;

    //起始、结束位置
    private int start, end;

    //待查找的词汇
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        //初始化计数器
        int result = 0;
        //如果行数小于10
        if (end - start < 10) {
            //处理每一行的数据
            result = processLines(document, start, end, word);
        } else {
            //行数大于10则进行任务分割
            int mid = (start + end) / 2;
            DocumentTask task1 = new DocumentTask(document, start, mid, word);
            DocumentTask task2 = new DocumentTask(document, mid, end, word);
            //提交任务（同步）
            invokeAll(task1, task2);
            try {
                //处理子任务返回的结果
                result = groupResults(task1.get(), task2.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        //返回结果
        return result;
    }

    //将子任务结果相加后返回
    private int groupResults(Integer number1, Integer number2) {
        return number1 + number2;
    }

    private int processLines(String[][] document, int start, int end, String word) {
        //创建装载行任务的集合
        ArrayList<LineTask> tasks = new ArrayList<>();
        //创建行任务
        for (int i = start; i < end; i++) {
            LineTask task = new LineTask(document[i], 0, document[i].length, word);
            tasks.add(task);
        }
        //执行所有任务
        invokeAll(tasks);
        //初始化计数器
        int result = 0;
        //从任务中获取结果
        for (int i = 0; i < tasks.size(); i++) {
            LineTask task = tasks.get(i);
            try {
                result = result + task.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        //返回结果
        return result;
    }

}
