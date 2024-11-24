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
 * @date 2024-10-11 09:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article_category")
public class ArticleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增主键策略
    private Integer id;

    // 用户ID
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "category_name", nullable = false, length = 100)  // 指定列名和约束
    private String categoryName;

    @Column(name = "category_alias", length = 100)
    private String categoryAlias;

    @Column(name = "create_time", updatable = false)  // 创建时间字段
    private Instant createTime;

    @Column(name = "update_time")  // 更新时间字段
    private Instant updateTime;

    private Boolean deleted;

    // 在插入时设置创建时间和更新时间
    @PrePersist
    public void onCreate() {
        Instant now = Instant.now();
        createTime = now; // 设置创建时间
        updateTime = now; // 设置更新时间
    }

    // 在更新时设置更新时间
    @PreUpdate
    public void onUpdate() {
        updateTime = Instant.now(); // 更新操作时设置更新时间
    }
}
