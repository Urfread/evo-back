package com.urfread.breaknews.core.test.redis;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * Person 类表示一个包含姓名、年龄和密码的用户对象。
 * 此类实现了 Serializable 接口，可以被序列化和反序列化。
 * Lombok 注解自动生成 getter、setter、构造器等常见方法。
 *
 * @Data：自动生成 getter、setter、toString、equals 和 hashCode 方法
 * @NoArgsConstructor：自动生成无参构造器
 * @AllArgsConstructor：自动生成全参构造器
 */
@Data // Lombok 注解，自动生成 getter、setter、toString、equals 和 hashCode 方法
@NoArgsConstructor // Lombok 注解，自动生成无参构造器
@AllArgsConstructor // Lombok 注解，自动生成全参构造器
@Builder // 简化new对象的操作
public class Person implements Serializable {

    // 序列化版本号。每次类的版本发生变化时，都应该修改该值，以保证兼容性。
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private String email;

    // 密码字段，使用 transient 关键字表示该字段在序列化时会被忽略
    private transient String password;  // 'transient' 使得该字段不会被序列化
}
