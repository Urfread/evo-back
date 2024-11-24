package com.urfread.breaknews.core.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024年11月17日20:21:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "practice_category")
public class PracticeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增主键策略
    private Long id;

    // 用户ID
    @Column(name = "uid")
    private Long uid;

    @Column(name = "category_name", nullable = false, length = 100)  // 指定列名和约束
    private String categoryName;

    @Column(name = "created_time", updatable = false)  // 创建时间字段
    private Instant createdTime;

    @Column(name = "updated_time")  // 更新时间字段
    private Instant updatedTime;

    private Boolean deleted;

    // 在插入时设置创建时间和更新时间
    @PrePersist
    public void onCreate() {
        Instant now = Instant.now();
        createdTime = now; // 设置创建时间
        updatedTime = now; // 设置更新时间
        deleted=false;
    }

    // 在更新时设置更新时间
    @PreUpdate
    public void onUpdate() {
        updatedTime = Instant.now(); // 更新操作时设置更新时间
    }
}
