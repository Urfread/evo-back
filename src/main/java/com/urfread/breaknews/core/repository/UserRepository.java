package com.urfread.breaknews.core.repository;

import com.urfread.breaknews.core.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUid(Integer uid);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.avatar = :uniqueFileName WHERE u.uid = :uid")
    int updateAvatar(@Param("uniqueFileName") String uniqueFileName, @Param("uid") Integer uid);
}
