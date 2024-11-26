package com.urfread.breaknews.core.learning.jpa.contact;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_email")
    private String email;

    // 是否删除（逻辑删除）
    private boolean deleted = false;

    // 创建时间
    private Instant createdTime;

    // 更新时间
    private Instant updatedTime;

    // 一个客户对应多个联系人
    @OneToMany(mappedBy = "customer")  // 对应 Contact 类中的 customer 字段
    private Set<Contact> contacts;

    // 在保存前自动设置创建时间和更新时间
    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        this.createdTime = now;
        this.updatedTime = now;
    }

    // 在更新前自动更新更新时间
    @PreUpdate
    public void preUpdate() {
        this.updatedTime = Instant.now();
    }
}
