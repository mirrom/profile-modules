package com.example.profile.repository.sub1profile;

import com.example.profile.model.sub1profile.QSub1Profile;
import com.example.profile.model.sub1profile.Sub1Profile;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;


@Repository
public interface Sub1ProfileRepository
        extends MongoRepository<Sub1Profile, ObjectId>, QuerydslPredicateExecutor<Sub1Profile>,
        QuerydslBinderCustomizer<QSub1Profile> {
    
    @Override
    default void customize(QuerydslBindings bindings, QSub1Profile root) {
        
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
    
}
