package com.urfread.breaknews.core.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-09 08:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserVO {
    private String username;
    private String password;
    private String rePassword;
}
