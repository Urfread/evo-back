package com.urfread.breaknews.core.learning.jpa.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // 你可以在这里添加自定义查询方法，例如：
    // List<Contact> findByName(String name);
}
