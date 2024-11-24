package com.urfread.breaknews.core.test.service;

import com.urfread.breaknews.core.common.entity.Tag;
import com.urfread.breaknews.core.common.entity.TagRelation;
import com.urfread.breaknews.core.service.TagService;
import com.urfread.breaknews.core.tag.TagNode;
import com.urfread.breaknews.core.tag.TagUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void testSaveTagTree() {
        String input = "#阶段/计划/大纲, #意义/创造价值/经验分享,#意义/创造价值/陪伴成长";
        // 调用保存方法
        tagService.saveTags(input,1,"article",1);
        // 调用查询方法
        List<Tag> tags=tagService.findTagsByUid(1);
        System.out.println(tags);
    }
    @Test
    public void testFindTagsByTagRelation() {
        List<Tag> tags = tagService.getTagsByEntityId(1, "article");
        // 输出每个 Tag 的 content
        //tags.forEach(tag -> System.out.println(tag.getContent()));
        TagNode root =TagUtil.buildTagTree(tags);
        root.printTree("");
    }
}
