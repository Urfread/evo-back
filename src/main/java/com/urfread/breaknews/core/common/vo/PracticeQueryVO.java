package com.urfread.breaknews.core.common.vo;

import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-11-18 09:48
 */
@Data
@Builder
public class PracticeQueryVO {
        // 总数
        private Integer total;
        // 文章列表
        private List<PracticeVO> items;
        // 查询参数
        private Long categoryId;  // 练习分类 ID
        private String state;         // 练习发布状态
        private Integer pageNum; // 页码
        private Integer pageSize;// 每页数量
}
