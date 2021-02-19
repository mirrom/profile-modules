package com.example.profile.repository.sub1profile;

import com.example.profile.model.sub1profile.Sub1Profile;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Sub1ProfileRepository extends MongoRepository<Sub1Profile, ObjectId> {

}

/*
public interface Sub1ProfileRepository
        extends MongoRepository<Sub1Profile, ObjectId>, QuerydslPredicateExecutor<Sub1Profile>,
        QuerydslBinderCustomizer<QSub1Profile> {
    
    default void customize(QuerydslBindings bindings, QProfile root) {
        
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
    
}
*/
