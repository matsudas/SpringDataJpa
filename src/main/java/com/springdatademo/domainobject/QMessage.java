package com.springdatademo.domainobject;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = -1174580253;

    public static final QMessage message = new QMessage("message");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath text = createString("text");

    public final StringPath userName = createString("userName");

    public QMessage(String variable) {
        super(Message.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QMessage(Path<? extends Message> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QMessage(PathMetadata<?> metadata) {
        super(Message.class, metadata);
    }

}

