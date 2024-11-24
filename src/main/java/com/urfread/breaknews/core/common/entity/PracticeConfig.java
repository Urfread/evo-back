package com.urfread.breaknews.core.common.entity;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "practice_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PracticeConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 自增ID，作为主键

    @Column(nullable = false)
    private Long uid;  // 用户ID

    @Column(nullable = false)
    private Long practiceId;  // 练习ID，关联练习表

    @Column(nullable = false)
    private Integer proficiency;  // 熟练度，默认0

    @Column(nullable = false)
    private Integer difficulty;  // 难度，默认0

    @Column(nullable = false)
    private Integer importance;  // 重要程度，默认0

    @Column(name = "created_time")
    private Instant createdTime = Instant.now();  // 创建日期，默认当前时间

    @Column(name = "updated_time")
    private Instant updatedTime = Instant.now();  // 更新时间，自动更新

    @Column
    private Boolean deleted;  // 是否删除，0为未删除，1为已删除

    @Column(name = "mastered", nullable = false)
    private Boolean mastered;  // 是否已掌握，默认未掌握

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
