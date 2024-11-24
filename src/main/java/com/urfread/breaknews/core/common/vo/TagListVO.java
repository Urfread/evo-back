package com.urfread.breaknews.core.common.vo;

import com.urfread.breaknews.core.common.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-20 15:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagListVO {
    private Integer uid;
    private String entityType;
    private Integer entityId;
    private List<Tag> tagList;
    private String tagStr;
}
