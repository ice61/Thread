package day04.code_4;

public class Results {

    //一维数组用来储存每一行包含指定数据的个数
    private int[] data;

    //根据传入参数创建一维数组
    public Results(int size) {
        data = new int[size];
    }

    //position为行号，value为一行中存在指定数据的个数
    public void setData(int position, int value) {
        data[position] = value;
    }

    //返回结果数组
    public int[] getData() {
        return data;
    }
}
