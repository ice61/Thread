package day06.code_3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {

    //必备参数
    private static final long serialVersionUID = 1L;

    //文件夹路径
    private String path;

    //文件后缀名
    private String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        //创建一个集合用于装载文件路径
        ArrayList<String> list = new ArrayList<>();
        //创建集合用于装载任务
        ArrayList<FolderProcessor> tasks = new ArrayList<>();
        //创建文件对象
        File file = new File(path);
        //得到文件夹下的全部文件
        File[] content = file.listFiles();
        //判断是否为空
        if (content != null) {
            //遍历集合
            for (int i = 0; i < content.length; i++) {
                //如果是文件夹就创建任务继续查找
                if (content[i].isDirectory()) {
                    FolderProcessor task = new FolderProcessor
                            (content[i].getAbsolutePath(), extension);
                    //异步执行任务
                    task.fork();
                    //将任务保存进集合
                    tasks.add(task);
                } else {
                    //检查文件是否符合要求，符合的话就装入集合
                    if (checkFile(content[i].getName())) {
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }
        }
        //如果文件集合容量超过50了就打印
        if (tasks.size() > 50) {
            System.out.printf("%s: %d tasks run\n",
                    file.getAbsolutePath(), tasks.size());
        }
        //整合子任务返回的结果
        addResultsFromTasks(list, tasks);
        //返回结果
        return list;
    }

    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
        //遍历任务集合
        for (FolderProcessor item : tasks) {
            //取得所有子任务返回的结果并装进集合中
            list.addAll(item.join());
        }
    }

    //检查文件后缀名是否符合要求
    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
