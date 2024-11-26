package com.urfread.breaknews.core.learning.jpa.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 你可以在这里添加自定义查询方法，例如：
    // List<Customer> findByName(String name);
}
