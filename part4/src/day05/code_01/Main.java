package day05.code_01;

public class Main {

    public static void main(String[] args) {
        //创建一个Server类对象
        Server server = new Server();
        //创建100个任务并使用server对象执行
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            server.executeTask(task);
        }
        //关闭server
        server.endServer();
    }

}
