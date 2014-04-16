package com.springdatademo.app;

import com.springdatademo.repository.CrudMessageRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springdatademo.AppConfig;
import com.springdatademo.domainobject.Message;

public class Demo {

    public static void main ( String [] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CrudMessageRepository mr = context.getBean(CrudMessageRepository.class);
        if ( mr == null ) {
            System.out.println("bean not initialized");
        }

        Message m = new Message("aaa", "bbb");
        Message saved = mr.save(m);
        System.out.println("===> inserted key: " + saved.getId());

        saved.setText("updated text");
        mr.save(saved);

        Message m1 = (Message)mr.findOne(1l);
        System.out.println("===> m1.getText(): " + m1.getText());

    }
}
