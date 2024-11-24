package com.urfread.breaknews.core.common.entity;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "practice_note")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PracticeNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 自增ID，作为主键

    @Column(nullable = false)
    private Long uid;  // 用户ID

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;  // 问题内容

    @Column(columnDefinition = "TEXT")
    private String hint;  // 提示，允许为空

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;  // 答案内容

    @Column(name = "created_time")
    private Instant createdTime;  // 创建日期，默认当前时间

    @Column(name = "updated_time")
    private Instant updatedTime;  // 更新时间，自动更新

    @Column
    private Boolean deleted;  // 是否删除，0为未删除，1为已删除
    // 在插入时设置创建时间和更新时间
    @PrePersist
    public void onCreate() {
        Instant now = Instant.now();
        createdTime = now; // 设置创建时间
        updatedTime = now; // 设置更新时间
        deleted = false;
    }

    // 在更新时设置更新时间
    @PreUpdate
    public void onUpdate() {
        updatedTime = Instant.now(); // 更新操作时设置更新时间
    }
}
