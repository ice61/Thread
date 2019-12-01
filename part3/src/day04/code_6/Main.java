package day04.code_6;

public class Main {

    public static void main(String[] args) {
        //创建我们自己的Phaser对象
        MyPhaser myPhaser = new MyPhaser();
        //创建五个Student对象并装入数组
        Student[] students = new Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(myPhaser);
            /*
             * 我们在创建Phaser对象时并没有传入参数
             * 因此调用register方法来增加注册数量
             * */
            myPhaser.register();
        }
        //创建五个线程并开启
        Thread[] threads = new Thread[5];
        for (int i = 0; i < students.length; i++) {
            threads[i] = new Thread(students[i], "Student" + i);
            threads[i].start();
        }
        //等待五个线程执行结束
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印MyPhaser对象的状态
        System.out.printf("Main: The phaser has finished: %s.\n",
                myPhaser.isTerminated());

    }

}
