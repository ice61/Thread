package day06.code_1;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {

    //根据传入的大小创建一个产品集合
    public List<Product> generate(int size) {
        //创建一个集合
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            //创建产品
            Product product = new Product();
            //设置名字
            product.setName("Product " + i);
            //统一设置初始价格为10，方便检查程序的正确性
            product.setPrice(10);
            //装入集合
            products.add(product);
        }
        //返回集合
        return products;
    }

}
