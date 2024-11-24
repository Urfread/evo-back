package com.urfread.breaknews.core.common.vo;

import com.urfread.breaknews.core.common.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-15 21:55
 */
@Builder
@Data
public class ArticleQueryVO {
    // 总数
    private Integer total;
    // 文章列表
    private List<ArticleVO> items;
    // 查询参数
    private Integer categoryId;  // 文章分类 ID
    private String state;         // 文章发布状态
    private Integer pageNum; // 页码
    private Integer pageSize;// 每页数量
}
