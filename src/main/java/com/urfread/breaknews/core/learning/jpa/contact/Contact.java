package com.urfread.breaknews.core.learning.jpa.contact;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_name")
    private String name;

    @Column(name = "contact_phone_number")
    private String phoneNumber;

    @Column(name = "contact_email")
    private String email;

    @Column(name = "contact_address")
    private String address;

    // 关联的客户（单向多对一关系）
    @ManyToOne
    @JoinColumn(name = "customer_id")  // 外键列名
    private Customer customer;

    // 是否删除（逻辑删除）
    private boolean deleted = false;  // 默认值为 false，表示没有被删除

    // 创建时间
    private Instant createdTime;

    // 更新时间
    private Instant updatedTime;

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
