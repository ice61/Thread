package day04.code_4;

import java.util.Random;

public class MatrixMock {

    //私有的二维数组data
    private int[][] data;

    //size为矩阵行数，length为列数，number为待查询的数据
    public MatrixMock(int size, int length, int number) {
        //counter用来记录待查询数据的个数
        int counter = 0;
        //根据参数创建一个二维数组
        data = new int[size][length];
        //创建随机数产生器
        Random random = new Random();
        //生成随机数填充数组
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                //如果生成的随机数等于待查询数据，计数器加一
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }
        //打印待查询数据的数量，方便检验程序最终的结果
        System.out.printf("Mock: There are %d ocurrences of number in" +
                "generated data\n", counter);
    }

    //根据行号返回一行数据
    public int[] getRow(int row) {
        //判断行号是否存在
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }

}
