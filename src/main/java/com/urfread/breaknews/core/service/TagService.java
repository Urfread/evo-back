package com.urfread.breaknews.core.service;

import com.urfread.breaknews.core.common.entity.Tag;
import com.urfread.breaknews.core.common.entity.TagRelation;
import com.urfread.breaknews.core.repository.TagRelationRepository;
import com.urfread.breaknews.core.repository.TagRepository;
import com.urfread.breaknews.core.tag.TagNode;
import com.urfread.breaknews.core.tag.TagUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagRelationRepository tagRelationRepository;

    public TagService(TagRepository tagRepository, TagRelationRepository tagRelationRepository) {
        this.tagRepository = tagRepository;
        this.tagRelationRepository = tagRelationRepository;
    }

    /**
     * 保存标签并构建标签树。
     *
     * @param input 标签字符串，以逗号分隔的标签格式。
     * @param uid 用户的唯一标识，用于关联标签与用户。
     */
    public void saveTags(String input, Integer uid,String entityType,Integer entityId) {
        // 删除与实体相关的旧关系
        List<TagRelation> oldRelations = tagRelationRepository.findByEntityIdAndEntityType(entityId, entityType);
        // 删除旧关系
        tagRelationRepository.deleteAll(oldRelations);
        saveTagTree(TagUtil.parseTagStringToTree(input), uid, entityType, entityId);
    }
    private void saveTagTree(TagNode node, Integer uid,String entityType,Integer entityId) {
        Tag rootTag=tagRepository.findTagsByUidAndContent(uid, "root");
        if (rootTag==null){
            rootTag =Tag.builder()
                    .uid(uid)
                    .content("root")
                    .parent(null) // 根节点没有父节点
                    .build();
            rootTag.onCreate();
            tagRepository.save(rootTag);
        }
        // 递归保存子节点
        saveChildren(rootTag, node.getChildren(),uid,entityType,entityId);
    }
    private void saveChildren(Tag parentTag, List<TagNode> children, Integer uid, String entityType, Integer entityId) {
        for (TagNode childNode : children) {
            // 检查标签是否已经存在
            Tag existingTag = tagRepository.findTagsByUidAndContent(uid, childNode.getContent());
            Tag childTag=null;

            if (existingTag != null) {
                // 如果标签已经存在，使用现有标签
                childTag = existingTag;
            }
            if(childTag==null){
                // 否则，创建新标签
                childTag = Tag.builder()
                        .uid(uid)
                        .content(childNode.getContent())
                        .parent(parentTag) // 设置父节点
                        .build();

                // 保存新标签
                childTag = tagRepository.save(childTag);
            }

            // 检查 TagRelation 是否已存在
            boolean relationExists = tagRelationRepository.existsByEntityIdAndTagId(entityId, childTag.getId());
            if (!relationExists) {
                // 保存关联关系
                tagRelationRepository.save(TagRelation.builder()
                        .entityId(entityId)
                        .entityType(entityType)
                        .tagId(childTag.getId())
                        .build());
            }

            // 递归保存子节点的子节点
            saveChildren(childTag, childNode.getChildren(), uid, entityType, entityId);
        }
    }
    public List<Tag> findTagsByUid(Integer uid) {
        return tagRepository.findTagsByUid(uid);
    }

    public List<Tag> getTagsByEntityId(Integer entityId, String entityType) {

        // 查找标签关系
        List<TagRelation> tagRelations = tagRelationRepository.findByEntityIdAndEntityType(entityId, entityType);
        if(tagRelations.isEmpty())return new ArrayList<>(0);
        // 获取标签 ID 列表
        List<Integer> tagIds = tagRelations.stream()
                .map(TagRelation::getTagId)
                .collect(Collectors.toList());
        // 根据标签 ID 查找标签
        List<Tag> res=tagRepository.findAllByIdIn(tagIds);
        res.add(tagRepository.findRootTagByUid(res.get(0).getUid()));
        return res;
    }

}
