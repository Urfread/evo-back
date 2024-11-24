package com.urfread.breaknews.core.common.vo;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-22 08:10
 */
@Data
@Builder
public class ArticleVO {
    private Integer id;       // 文章ID
    private Integer uid;      // 用户ID
    private String title;     // 文章标题
    private String content;   // 文章内容
    private String coverImg;  // 封面图片
    private String state;     // 文章状态
    private Integer categoryId;// 分类ID
    private Instant createTime;// 创建时间
    private Instant updateTime;// 更新时间
    private boolean deleted;   // 删除标记
    private String tagStr;     // 标签字符串
}
