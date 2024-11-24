package com.urfread.breaknews.core.repository;

import com.urfread.breaknews.core.common.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findTagsByUid(Integer uid);
    @Query("SELECT t FROM Tag t WHERE t.uid = :uid AND t.content = :content")
    Tag findTagsByUidAndContent(@Param("uid") Integer uid, @Param("content") String content);

    List<Tag> findAllByIdIn(List<Integer> ids);
    @Query("SELECT t FROM Tag t WHERE t.uid = :uid AND t.content = 'root' AND t.parent IS NULL")
    Tag findRootTagByUid(@Param("uid") Integer uid);
}
