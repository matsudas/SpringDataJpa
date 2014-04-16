package com.springdatademo.repository;

import org.springframework.data.repository.CrudRepository;

import com.springdatademo.domainobject.Message;

public interface CrudMessageRepository extends CrudRepository<Message , Long >{

}
