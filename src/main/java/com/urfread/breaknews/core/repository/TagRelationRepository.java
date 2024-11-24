package com.urfread.breaknews.core.repository;

import com.urfread.breaknews.core.common.entity.TagRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TagRelationRepository extends JpaRepository<TagRelation, Integer> {

    void deleteByEntityIdAndEntityType(Integer entityId, String entityType);
    @Query("SELECT tr FROM TagRelation tr WHERE tr.entityId = :entityId AND tr.entityType = :entityType")
    List<TagRelation> findByEntityIdAndEntityType(@Param("entityId") Integer entityId, @Param("entityType") String entityType);
    @Query("SELECT COUNT(tr) > 0 FROM TagRelation tr WHERE tr.entityId = :entityId AND tr.tagId = :tagId")
    boolean existsByEntityIdAndTagId(@Param("entityId") Integer entityId, @Param("tagId") Integer tagId);

}
