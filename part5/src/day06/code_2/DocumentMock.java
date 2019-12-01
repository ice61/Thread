package day06.code_2;


import java.util.Random;

public class DocumentMock {

    //从以下词汇中选择词语组成文档
    private String words[] = {
            "the", "hello", "goodbye", "packt", "java",
            "thread", "pool", "random", "class", "main"
    };

    public String[][] generateDocument(int numLines, int numWords, String word) {
        //记录指定词汇出现的次数，便于后期判断程序对错
        int counter = 0;
        //创建二维数组
        String[][] document = new String[numLines][numWords];
        //随机数生成器
        Random random = new Random();
        //填充数组
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numWords; j++) {
                //随机选取词汇并填充
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                //如果是指定词汇，计数器加一
                if (document[i][j] == word) {
                    counter++;
                }

            }
        }
        //打印指定词汇出现的次数
        System.out.printf("DocumentMock: The word appears " +
                "%d times in the document\n", counter);
        //返回文档
        return document;
    }

}
