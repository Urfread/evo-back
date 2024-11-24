package com.urfread.breaknews.core.service;//package com.urfread.breaknews.core.service;
//
//import com.urfread.breaknews.core.common.entity.TagRelation;
//import com.urfread.breaknews.core.repository.TagRelationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TagRelationService {
//
//    private final TagRelationRepository tagRelationRepository;
//
//    @Autowired
//    public TagRelationService(TagRelationRepository tagRelationRepository) {
//        this.tagRelationRepository = tagRelationRepository;
//    }
//
//    // 添加标签关系
//    public TagRelation addTagRelation(TagRelation tagRelation) {
//        return tagRelationRepository.save(tagRelation);
//    }
//
//    // 根据实体 ID 和类型获取标签关系
//    public List<TagRelation> getTagRelations(Integer entityId, String entityType) {
//        return tagRelationRepository.findByEntityIdAndEntityType(entityId, entityType);
//    }
//
//    // 删除标签关系
//    public void deleteTagRelations(Integer entityId, String entityType) {
//        tagRelationRepository.deleteByEntityIdAndEntityType(entityId, entityType);
//    }
//}
