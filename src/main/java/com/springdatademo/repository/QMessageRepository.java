package com.springdatademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.springdatademo.domainobject.Message;

public interface QMessageRepository extends JpaRepository<Message, Long>, QueryDslPredicateExecutor<Message> {
}
