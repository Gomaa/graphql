package com.example.services.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.Party;
import com.example.repository.PartyCrudRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllPartiesDataFetcher implements DataFetcher<List<Party>>{

    
    @Autowired
    private PartyCrudRepo partyCrud;
    
    @Override
    public List<Party> get(DataFetchingEnvironment environment) throws Exception {

//        List<Party> myList = new ArrayList<>();
//        Iterator<Party> iterator = partyCrud.findAll().iterator();
//        iterator.forEachRemaining(myList::add);
//        return myList;
        return partyCrud.findAll();
    }

}
