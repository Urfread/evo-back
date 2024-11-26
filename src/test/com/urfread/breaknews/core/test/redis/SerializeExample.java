package com.urfread.breaknews.core.test.redis;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class SerializeExample {
    public static void main(String[] args) {
        // 创建 Person 对象
        Person person = Person.builder()
                .name("John")
                .age(30)
                .password("secret123")
                .build();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            // 序列化对象到文件
            oos.writeObject(person);
            System.out.println("Person object serialized successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
