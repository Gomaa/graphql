package com.example.services.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.Party;
import com.example.repository.PartyCrudRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;


@Component
public class PartyDataFetcher implements DataFetcher<Party>{

    
    @Autowired
    private PartyCrudRepo partyRepo;
    
    
    @Override
    public Party get(DataFetchingEnvironment environment) throws Exception {
        Long id = (Long) environment.getArgument("id");
        
        return partyRepo.findById(id).get();
    }
    

}
