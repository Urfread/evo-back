package com.urfread.breaknews.core.common.vo;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-11-18 09:50
 */
@Builder
@Data
public class PracticeVO {

    private Long id;       // 练习ID
    private Long uid;      // 用户ID
    private String question;     // 练习标题
    private String hint; //提示
    private String answer;   // 练习内容
    private String state;     // 练习状态
    private Long categoryId;// 分类ID
    private Instant createTime;// 创建时间
    private Instant updateTime;// 更新时间
    private Boolean deleted;   // 删除标记
    private String tagStr;     // 标签字符串
}
