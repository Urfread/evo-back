//package com.urfread.breaknews.core.learning.jpa.contact;
//
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.time.Instant;
//
//@Component
//public class DataLoader2 {
//
//    @Resource
//    private CustomerRepository customerRepository;
//    @Resource
//    private ContactRepository contactRepository;
//
//    @PostConstruct
//    public void loadData() {
//        // 创建客户数据
//        Customer customer1 = Customer.builder()
//                .name("John Doe")
//                .email("john.doe@example.com")
//                .createdTime(Instant.now())
//                .updatedTime(Instant.now())
//                .build();
//        Customer customer2 = Customer.builder()
//                .name("Jane Smith")
//                .email("jane.smith@example.com")
//                .createdTime(Instant.now())
//                .updatedTime(Instant.now())
//                .build();
//
//        // 保存客户数据
//        customerRepository.save(customer1);
//        customerRepository.save(customer2);
//
//        // 创建联系人数据
//        Contact contact1 = Contact.builder()
//                .name("John's Contact 1")
//                .phoneNumber("123-456-7890")
//                .email("contact1@example.com")
//                .address("123 Main St, City, Country")
//                .customer(customer1)  // 关联客户
//                .createdTime(Instant.now())
//                .updatedTime(Instant.now())
//                .build();
//
//        Contact contact2 = Contact.builder()
//                .name("John's Contact 2")
//                .phoneNumber("234-567-8901")
//                .email("contact2@example.com")
//                .address("456 Oak St, City, Country")
//                .customer(customer1)
//                .createdTime(Instant.now())
//                .updatedTime(Instant.now())
//                .build();
//
//        Contact contact3 = Contact.builder()
//                .name("Jane's Contact")
//                .phoneNumber("345-678-9012")
//                .email("contact3@example.com")
//                .address("789 Pine St, City, Country")
//                .customer(customer2)
//                .createdTime(Instant.now())
//                .updatedTime(Instant.now())
//                .build();
//
//        // 保存联系人数据
//        contactRepository.save(contact1);
//        contactRepository.save(contact2);
//        contactRepository.save(contact3);
//    }
//}
