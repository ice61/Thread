package day03.code_7;

public class FileMock {

    //存放数据的数组
    private String content[];

    //模拟数据的行号
    private int index;

    //带有两个参数的构造函数
    public FileMock(int size, int length) {
        //创建一个size大小的字符串数组
        this.content = new String[size];
        //外部循环size次，目的是将数组填满
        for (int i = 0; i < size; i++) {
            //使用字符串构造器来拼接数据，减少无用字符串常量的产生
            StringBuilder buffer = new StringBuilder(length);
            //内部循环length次，表示每条数据长为length
            for (int j = 0; j < length; j++) {
                //这里使用随机数作为数据
                int temp = (int) (Math.random() * 255);
                buffer.append(temp);
            }
            //将创建好的一条数据放入数组中对应的位置
            content[i] = buffer.toString();
        }
        //将行号置为0
        this.index = 0;
    }

    //用来判断文件中是否还有未读取的数据的方法
    public boolean hasMoreLines() {
        //index等于数组长度时表示所有数据均读取过
        return index < content.length;
    }

    //从模拟文件中得到一行数据
    public String getLine() {
        //首先判断是否存在未读取的数据
        if (hasMoreLines()) {
            //打印读取到的行号
            System.out.println("Mock: " + index);
            //返回数据后index自增
            return content[index++];
        }
        return null;
    }
}
