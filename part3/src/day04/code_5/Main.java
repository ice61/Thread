package day04.code_5;

import java.util.concurrent.Phaser;

public class Main {

    public static void main(String[] args) {
        //创建phaser对象，并设置参与阶段同步的线程数为3
        Phaser phaser = new Phaser(3);
        //创建三个FileSearch对象并将其作为参数来创建线程并运行
        FileSearch system = new FileSearch("C:\\", "exe", phaser);
        FileSearch apps = new FileSearch("D:\\", "exe", phaser);
        FileSearch files = new FileSearch("F:\\", "exe", phaser);
        Thread systemThreaad = new Thread(system, "System");
        systemThreaad.start();
        Thread appsThreaad = new Thread(apps, "Apps");
        appsThreaad.start();
        Thread filesThreaad = new Thread(files, "Files");
        filesThreaad.start();
        //主线程等待三个线程运行结束
        try {
            systemThreaad.join();
            appsThreaad.join();
            filesThreaad.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印Phaser对象是否已经终止
        System.out.println("Terminated: " + phaser.isTerminated());
    }

}
