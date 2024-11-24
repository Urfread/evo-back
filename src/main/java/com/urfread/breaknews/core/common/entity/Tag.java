package com.urfread.breaknews.core.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 用户ID
    @Column(name = "uid")
    private Integer uid;

    // 标签内容
    @Column(name = "content", nullable = false)
    private String content;

    // 父标签ID
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Tag parent;

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
        deleted = false;
    }

    @PreUpdate
    public void onUpdate() {
        updateTime = Instant.now();
    }
}
