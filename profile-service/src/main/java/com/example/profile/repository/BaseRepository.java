package com.example.profile.repository;

import com.example.profile.model.BaseModel;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<M extends BaseModel, Q extends EntityPath<?>>
        extends MongoRepository<M, ObjectId>, QuerydslPredicateExecutor<M>, QuerydslBinderCustomizer<Q> {
    
    @Override
    default void customize(QuerydslBindings bindings, Q root) {
        
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
    
}
