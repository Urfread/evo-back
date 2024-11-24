package com.urfread.breaknews.core.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tag_relations")
public class TagRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 自增主键
    @Column(name = "entity_id", nullable = false)
    private Integer entityId;  // 实体 ID
    @Column(name = "entity_type", nullable = false)
    private String entityType;  // 实体类型，例如 "note", "article", "practice"
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;  // 标签 ID
}
