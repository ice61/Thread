package day03.code_7;

public class Producer implements Runnable {

    //模拟文件对象
    private FileMock fileMock;
    //缓冲器对象
    private Buffer buffer;

    //通过构造方法传入两个对象的引用
    public Producer(FileMock fileMock, Buffer buffer) {
        this.fileMock = fileMock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        //文件存在未读数据
        buffer.setPendingLines(true);
        //当文件存在未读数据时
        while (fileMock.hasMoreLines()) {
            //不断读取数据并装入缓冲区
            String line = fileMock.getLine();
            buffer.insert(line);
        }
        //文件不存在未读数据
        buffer.setPendingLines(false);
    }
}
