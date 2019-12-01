package day08.code_07;

public class Task extends MyWorkerTask {

    //必备元素
    private static final long serialVersionUID = 1L;

    //数组
    private int array[];

    //任务起始、终止位置
    private int start, end;

    //构造方法
    public Task(String name, int[] array, int start, int end) {
        super(name);
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        //如果任务过大，进行拆分
        if (end - start > 100) {
            int mid = (start + end) / 2;
            Task task1 = new Task(this.getName() + "1", array, start, mid);
            Task task2 = new Task(this.getName() + "2", array, mid, end);
            //同步执行
            invokeAll(task1, task2);
        } else {
            //将范围内的数组元素自增
            for (int i = start; i < end; i++) {
                array[i]++;
            }
        }
        //修庙50毫秒
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
