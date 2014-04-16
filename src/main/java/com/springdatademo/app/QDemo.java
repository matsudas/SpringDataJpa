package com.springdatademo.app;

import com.mysema.query.types.Predicate;
import com.springdatademo.AppConfig;
import com.springdatademo.domainobject.Message;
import com.springdatademo.repository.MessagePredicates;
import com.springdatademo.repository.QMessageRepository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shinya on 2014/04/16.
 */
public class QDemo {
    public static void main ( String [] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        QMessageRepository mr = context.getBean(QMessageRepository.class);
        if ( mr == null ) {
            System.out.println("bean not initialized");
        }

        Message m = null;
        for(int i = 0; i < 3; i++) {
            System.out.println("===> insert: " + i);
            m = new Message("aaa", "no=" + i);
            mr.save(m);
        }

        //Message m1 = (Message)mr.findOne(1l);
        //System.out.println("===> m1.getText(): " + m1.getText());
        search(mr, "aaa");

    }

    public static void search(QMessageRepository repository, String searchTerm) {
        Predicate contactPredicate = MessagePredicates.userNameOrTextStartsWith(searchTerm);

        Iterable<Message> messages = repository.findAll(contactPredicate);
        for(Message m : messages) {
            System.out.println("===> " + m.getUserName());
        }
    }

}
