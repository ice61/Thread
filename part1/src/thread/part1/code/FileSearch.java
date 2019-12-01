package thread.part1.code;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable {

    //文件夹路径
    private String initPath;
    //待搜索文件的名称
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        //以文件夹路径作为参数创建一个文件
        File file = new File(initPath);
        //如果文件是一个文件夹，执行directoryProcess方法
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
                //捕获异常并进行相应的中断响应
            } catch (InterruptedException e) {
                System.out.printf("%s: The search has been interrupted",Thread.currentThread().getName());
            }
        }
    }

    public void directoryProcess(File file) throws InterruptedException{
        //以数组形式得到文件夹中的所有文件
        File[] files = file.listFiles();
        /*
        * 如果数组不为空，遍历数组
        * 遍历到的文件如果是文件夹，继续执行directoryProcess方法，向下深入
        * 如果是文件，则执行fileProcess方法
        * 通过静态方法interrupted检测是否有中断，有的话抛出异常
        * */
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    directoryProcess(files[i]);
                } else {
                    fileProcess(files[i]);
                }
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }
    }

    public void fileProcess(File file) throws InterruptedException{
        //将文件名和待查询文件名进行对比，如果一样则打印结果
        if (file.getName().equals(fileName)) {
            System.out.println(Thread.currentThread().getName() + " : " + file.getAbsolutePath());
        }
        //检测中断，如果有中断则抛出异常
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    public static void main(String[] args) {
        //通过构造函数传参，这里设置文件夹为C盘
        FileSearch fileSearch = new FileSearch("C:/", "netty-bom-4.1.27.Final.pom.part");
        Thread thread = new Thread(fileSearch);
        thread.start();
        try {
            //主线程休眠10秒
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //发起中断
        thread.interrupt();
    }
}
