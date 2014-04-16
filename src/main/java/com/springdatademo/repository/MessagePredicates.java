package com.springdatademo.repository;

import com.mysema.query.types.Predicate;
import com.springdatademo.domainobject.QMessage;

/**
 * Created by shinya on 2014/04/16.
 */
public class MessagePredicates {
    public static Predicate userNameOrTextStartsWith(final String searchTerm) {
        QMessage message = QMessage.message;
        return message.userName.startsWithIgnoreCase(searchTerm)
                .or(message.text.startsWithIgnoreCase(searchTerm));
    }
}
