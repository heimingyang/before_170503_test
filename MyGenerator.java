package com.example.before_170503_test;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

 /* Created by 黑明阳 on 2017/4/18.
 */

public class MyGenerator {
    public static void main(String []arg){

        //指定GreenDao帮我们生成的文件存放的包名
        Schema schema=new Schema(1,"com.example.before_170503_test");

        //指定创建数据库的表名
        Entity entity=schema.addEntity("Model");

        //指定表的列名
        entity.addIdProperty();
        entity.addStringProperty("title");
        entity.addStringProperty("source");
        entity.addStringProperty("firstImg");
        entity.addStringProperty("mark");
        entity.addStringProperty("url");

        try {
            //指定GreenDao帮我们生成的文件存放的位置
            new DaoGenerator().generateAll(schema,"D:\\AndroidStudio\\android-sdk-windows\\before_170503_test\\app\\src\\main\\java");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
