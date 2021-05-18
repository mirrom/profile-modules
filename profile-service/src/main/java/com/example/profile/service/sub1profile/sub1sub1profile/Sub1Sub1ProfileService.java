package com.example.profile.service.sub1profile.sub1sub1profile;

import com.example.profile.model.sub1profile.sub1sub1profile.QSub1Sub1Profile;
import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;
import com.example.profile.predicate.BasePredicateBuilder;
import com.example.profile.repository.BaseRepository;
import com.example.profile.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Sub1Sub1ProfileService extends BaseService<Sub1Sub1Profile, QSub1Sub1Profile> {
    
    @Autowired
    public Sub1Sub1ProfileService(BaseRepository<Sub1Sub1Profile, QSub1Sub1Profile> repository) {
        
        super(repository);
    }
    
    @Override
    public Iterable<Sub1Sub1Profile> get(int page, int size, String sortDirection, String sortBy, String search) {
        
        BasePredicateBuilder<Sub1Sub1Profile> basePredicateBuilder =
                new BasePredicateBuilder<>(Sub1Sub1Profile.class, Sub1Sub1Profile.class.getName());
        
        basePredicateBuilder.from(search);
        
        var booleanExpression = basePredicateBuilder.build();
        
        return super.get(page, size, sortDirection, sortBy, booleanExpression);
    }
    
}
