package day04.code_4;

public class Grouper implements Runnable {

    //结果类
    private Results results;

    //构造方法
    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        //整个矩阵包含待查询数据的个数
        int finalResult = 0;
        //打印提示语句
        System.out.println("Grouper: Processing results...");
        //遍历一维结果数组，求和
        int[] data = results.getData();
        for (int d : data) {
            finalResult += d;
        }
        //打印结果
        System.out.printf("Grouper: Total result: %d\n",
                finalResult);
    }
}
