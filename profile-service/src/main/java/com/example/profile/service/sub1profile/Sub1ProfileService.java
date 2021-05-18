package com.example.profile.service.sub1profile;

import com.example.profile.model.sub1profile.QSub1Profile;
import com.example.profile.model.sub1profile.Sub1Profile;
import com.example.profile.predicate.BasePredicateBuilder;
import com.example.profile.repository.BaseRepository;
import com.example.profile.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Sub1ProfileService extends BaseService<Sub1Profile, QSub1Profile> {
    
    @Autowired
    public Sub1ProfileService(BaseRepository<Sub1Profile, QSub1Profile> repository) {
        
        super(repository);
    }
    
    @Override
    public Iterable<Sub1Profile> get(int page, int size, String sortDirection, String sortBy, String search) {
        
        BasePredicateBuilder<Sub1Profile> basePredicateBuilder =
                new BasePredicateBuilder<>(Sub1Profile.class, Sub1Profile.class.getName());
        
        basePredicateBuilder.from(search);
        
        var booleanExpression = basePredicateBuilder.build();
        
        return super.get(page, size, sortDirection, sortBy, booleanExpression);
    }
    
}
