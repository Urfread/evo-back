package com.urfread.breaknews.core.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 用户ID
    @Column(name = "uid", nullable = false)
    private Integer uid;

    // 文章标题
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    // 文章内容
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // 封面图片
    @Column(name = "cover_img", length = 255)
    private String coverImg;

    // 文章状态（草稿或已发布）
    @Column(name = "state", length = 50, nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '草稿'")
    private String state;

    // 分类ID
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    // 创建时间
    @Column(name = "create_time", updatable = false)
    private Instant createTime;

    // 更新时间
    @Column(name = "update_time")
    private Instant updateTime;

    // 删除标记
    @Column(name = "deleted", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean deleted;

    // 设置创建和更新时间
    @PrePersist
    public void onCreate() {
        Instant now = Instant.now();
        createTime = now;
        updateTime = now;
    }

    @PreUpdate
    public void onUpdate() {
        updateTime = Instant.now();
    }
}
