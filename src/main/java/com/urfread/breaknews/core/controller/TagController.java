package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.entity.Tag;
import com.urfread.breaknews.core.common.entity.TagRelation;
import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.vo.TagListVO;
import com.urfread.breaknews.core.service.TagService;
import com.urfread.breaknews.core.tag.TagNode;
import com.urfread.breaknews.core.tag.TagUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-20 15:49
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // 获取文章的标签
    @GetMapping("/findTags")
    public ResultData<String> getTagsByTagRelation(TagRelation tagRelation) {
        // 调用服务层的方法，根据实体 ID 和实体类型获取标签列表
        List<Tag> tags = tagService.getTagsByEntityId(tagRelation.getEntityId(), tagRelation.getEntityType());

        // 使用 TagUtil 构建标签树
        TagNode rootNode = TagUtil.buildTagTree(tags);

        // 将标签树转换为字符串形式，参数 'true' 表示只包含完整路径
        String tagsAsString = TagUtil.tagsToString(rootNode, true);

        // 返回成功的结果数据，包含标签字符串
        return ResultData.success(tagsAsString);
    }

    // 录入标签
    @PostMapping("/addTags")
    public ResultData<TagListVO> addTags(@RequestBody TagListVO tagListVO, HttpServletRequest req) {
        String uid=(String)req.getAttribute("uid");
        tagService.saveTags(tagListVO.getTagStr(), Integer.valueOf(uid),tagListVO.getEntityType(),tagListVO.getEntityId());
        return ResultData.success(tagListVO);
    }
}
