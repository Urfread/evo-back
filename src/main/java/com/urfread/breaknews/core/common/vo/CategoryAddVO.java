package com.urfread.breaknews.core.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-11 11:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryAddVO {
    private String categoryName;
    private String categoryAlias;
}
