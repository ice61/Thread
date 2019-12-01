package day04.code_5;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable {

    //查找的文件夹
    private String initPath;
    //待查找文件的拓展名
    private String end;
    //用来存储查找到的文件的完成路径
    private List<String> results;

    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        results = new ArrayList<>();
    }

    private void directoryProcess(File file) {
        //找到当前文件夹下的所有文件和文件夹
        File[] files = file.listFiles();
        //如果文件夹为空则直接返回
        if (files != null) {
            //遍历文件数组
            for (int i = 0; i < files.length; i++) {
                //如果是文件夹则递归调用directoryProcess继续深入
                if (files[i].isDirectory()) {
                    directoryProcess(files[i]);
                } else {
                    //如果是文件则调用fileProcess判断是否为要找的文件
                    fileProcess(files[i]);
                }
            }
        }
    }

    private void fileProcess(File file) {
        //判断文件的拓展名是否符合查询要求
        if (file.getName().endsWith(end)) {
            //如果符合就填入集合中
            results.add(file.getAbsolutePath());
        }
    }

    private void filterResults() {
        //创建一个新的集合对象
        ArrayList<String> newResults = new ArrayList<>();
        //得到当前时间
        long time = new Date().getTime();
        //遍历集合
        for (int i = 0; i < results.size(); i++) {
            //根据全路径得到文件
            File file = new File(results.get(i));
            //得到文件最后更改的时间
            long fileData = file.lastModified();
            //判断时间差是否小于一天
            if (time - fileData < TimeUnit.MILLISECONDS.
                    convert(1, TimeUnit.DAYS)) {
                //如果小于就添加到新的集合中
                newResults.add(results.get(i));
            }
        }
        //将新的集合赋值给结果
        results = newResults;
    }

    private boolean checkResults() {
        //如果结果为空
        if (results.isEmpty()) {
            //打印相关信息
            System.out.printf("%s: Phase %d: 0 results\n",
                    Thread.currentThread().getName(),
                    phaser.getPhase());
            System.out.printf("%s: Phase %d: End\n",
                    Thread.currentThread().getName(),
                    phaser.getPhase());
            //通知phaser对象当前线程已完成当前阶段且不再参与接下来的操作
            phaser.arriveAndDeregister();
            return false;
        } else {
            //打印相关信息
            System.out.printf("%s: Phase %d: %d results\n",
                    Thread.currentThread().getName(),
                    phaser.getPhase(), results.size());
            //通知phaser对象当前线程已完成当前阶段并进入等待状态
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo() {
        //打印结果集合中的所有文件
        for (String file : results) {
            System.out.printf("%s: %s\n",
                    Thread.currentThread().getName(),
                    file);
        }
        //通知phaser对象当前线程已完成当前阶段并进入等待状态
        phaser.arriveAndAwaitAdvance();
    }

    @Override
    public void run() {
        //在run方法第一句调用此方法保证所有线程同时开始
        phaser.arriveAndAwaitAdvance();
        //打印线程信息
        System.out.printf("%s: Starting\n",
                Thread.currentThread().getName());
        //根据路径进行搜索
        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        }
        //检查结果是否为空，如果为空直接结束
        if (!checkResults()) {
            return;
        }
        //过滤第一次搜索出来的结果
        filterResults();
        //检查结果是否为空，如果为空直接结束
        if (!checkResults()) {
            return;
        }
        //打印结果
        showInfo();
        //通知phaser对象当前线程已完成当前阶段且不再参与接下来的操作
        phaser.arriveAndDeregister();
        //打印线程结束信息
        System.out.printf("%s: Work completed\n",
                Thread.currentThread().getName());
    }
}
